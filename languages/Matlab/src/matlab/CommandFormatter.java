package matlab;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import matlab.ExtractionParser.Terminals;

import beaver.Scanner;
import beaver.Symbol;

public class CommandFormatter {
    private final List<Symbol> formattedSymbols;

    private CommandFormatter() {
        this.formattedSymbols = new ArrayList<Symbol>();
    }

    public static List<Symbol> format(List<Symbol> originalSymbols) throws CommandException {
        if(isNotCmd(originalSymbols)) {
            return originalSymbols == null ? null : new ArrayList<Symbol>(originalSymbols);
        }
        List<Symbol> argSymbols = rescan(originalSymbols);
        CommandFormatter cf = new CommandFormatter();
        return cf.reformat(argSymbols);
    }

    private static List<Symbol> rescan(List<Symbol> originalSymbols) throws CommandException {
        StringBuffer textBuf = new StringBuffer();
        for(Symbol sym : originalSymbols) {
            textBuf.append(sym.value);
        }

        CommandScanner scanner = new CommandScanner(new StringReader(textBuf.toString()));
        int basePos = originalSymbols.get(0).getStart();
        int baseLine = Symbol.getLine(basePos);
        int baseCol = Symbol.getColumn(basePos);
        scanner.setBasePosition(baseLine, baseCol);

        List<Symbol> argSymbols = new ArrayList<Symbol>();
        while(true) {
            Symbol curr = null;
            try {
                curr = scanner.nextToken();
            } catch (Scanner.Exception e) {
                throw new CommandException(e.line, e.column, e.getMessage());
            } catch (IOException e) {
                //can't happen - using a StringReader
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            if(curr.getId() == ExtractionParser.Terminals.EOF) {
                break;
            }
            argSymbols.add(curr);
        }
        return argSymbols;
    }

    //TODO-AC: track position changes
    private List<Symbol> reformat(List<Symbol> unformattedSymbols) {
        List<Symbol> reversedUnformattedSymbols = new ArrayList<Symbol>(unformattedSymbols);
        Collections.reverse(reversedUnformattedSymbols);
        boolean first = true;
        formattedSymbols.add(new Symbol(")")); //TODO-AC: id?
        for(Symbol sym : reversedUnformattedSymbols) {
            if(!isFiller(sym)) {
                if(!first) {
                    formattedSymbols.add(new Symbol(Terminals.COMMA, ","));
                }
                first = false;
            }
            formattedSymbols.add(sym);
        }
        formattedSymbols.add(new Symbol("("));
        Collections.reverse(formattedSymbols);
        return formattedSymbols;
    }
    
    private static boolean isFiller(Symbol sym) {
        short type = sym.getId();
        return type == Terminals.OTHER_WHITESPACE || type == Terminals.ELLIPSIS_COMMENT;
    }

    private static boolean isNotCmd(List<Symbol> originalSymbols) {
        //no args => not a command
        if(originalSymbols == null || originalSymbols.isEmpty()) {
            return true;
        }

        //transpose => no args => not a command
        switch(originalSymbols.get(0).getId()) {
        case Terminals.MTRANSPOSE:
        case Terminals.ARRAYTRANSPOSE:
            return true;
        }

        Symbol firstNonWhitespace = null;
        for(Symbol sym : originalSymbols) {
            if(!isFiller(sym)) {
                if(firstNonWhitespace == null) {
                    firstNonWhitespace = sym;
                    switch(sym.getId()) {
                    //paren or assign => definitely not a command
                    case Terminals.PARENTHESIZED:
                    case Terminals.ASSIGN:
                        return true;
                    //operator => command iff nothing important follows
                    case Terminals.LT:
                    case Terminals.GT:
                    case Terminals.LE:
                    case Terminals.GE:
                    case Terminals.EQ:
                    case Terminals.NE:
                    case Terminals.AND:
                    case Terminals.OR:
                    case Terminals.SHORTAND:
                    case Terminals.SHORTOR:
                    case Terminals.COLON:
                    case Terminals.MTIMES:
                    case Terminals.ETIMES:
                    case Terminals.MDIV:
                    case Terminals.EDIV:
                    case Terminals.MLDIV:
                    case Terminals.ELDIV:
                    case Terminals.PLUS:
                    case Terminals.MINUS:
                    case Terminals.MPOW:
                    case Terminals.EPOW:
                    case Terminals.DOT:
                        break;
                    //otherwise => definitely a command
                    default:
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
