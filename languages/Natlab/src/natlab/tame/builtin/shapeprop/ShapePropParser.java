package natlab.tame.builtin.shapeprop;

import java.util.ArrayList;
import natlab.tame.builtin.shapeprop.ast.*;
import beaver.*;
import java.util.*;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.2
 * from the grammar specification "shapeprop.grammar".
 */
public class ShapePropParser extends Parser {
	static public class Terminals {
		static public final short EOF = 0;
		static public final short LOWERCASE = 1;
		static public final short UPPERCASE = 2;
		static public final short ANY = 3;
		static public final short LSPAREN = 4;
		static public final short SCALAR = 5;
		static public final short LRPAREN = 6;
		static public final short SQUOTATION = 7;
		static public final short RRPAREN = 8;
		static public final short NUMBER = 9;
		static public final short ID = 10;
		static public final short EQUAL = 11;
		static public final short RSPAREN = 12;
		static public final short COMMA = 13;
		static public final short OROR = 14;
		static public final short ARROW = 15;
		static public final short OR = 16;
		static public final short QUESTION = 17;
		static public final short MULT = 18;
		static public final short PLUS = 19;
	}

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9nzb4bJ4r4KXKyd69eCWb4WGCG44b1HSG0L701nOk5MD#vSMcwqIXPMMMnqmHzmmS85Iyj" +
		"v#W7#0UTvdi4upufUptlzw3ITngI#lkUTUz$jrwy709Um0FKYQ4GF8cX0Eren7s64aC1CL2" +
		"AkyvTByZEq$qVxSrsV7igrbtUzhUZ5HjqfXuNo0dFGETL358jBl3OaIxmATkz4dRkoMevha" +
		"88jpr7byTEOZLdqlRKjrAle5PqKbc8HZvDZTORItYiObyiDT60zrJgiOUzge$lqzIv3HXwW" +
		"6wlTkTrOYtd8eWdDQito5qkmahqte4kken#RiGanfEG#jg1CxW7oaBjI3V2iQeNrtBiqTxM" +
		"HNKBSwpBkjigt1EI3vYclqkTPRgS8xr2SQutwl0J7VY$AIh#dkdizDQxXTMPO6$Vu2Rq35L" +
		"DgRMgRNj6fu4wKy#WzGsbl9zgy8aSvlHqTqJ7FwurnltFkNAMh#1maUSUSNIfdhQNxgB#Qc" +
		"T7djtdVOdm7OgmP68B#jPCGEKswI2Ef8r6Jxo2ZIdlXRuU9lIQsaVrakx$Mv7UIVgD7oC60" +
		"ccraTu3VHmQDFcRYNh8heFOusMFq2HE7oI5oqbTxXEmZfyWPSewS9HV8UN9HrOb8ON8EzOH" +
		"tNHuz4h2MWG1lqE$vyhP73mVaa$#R7r1$O1f$AC0xF4tjKTzur3SUuCuKb72EZ1FocyTVya" +
		"R6POpgDNbfSi$agJoHn$9AVaf1ViXt7h$89$ae7zHy#IpVvAkyrxNlo5kj7ibTkIEtjRv5R" +
		"ffkr#IgN95nCnf3yTVExuZ5BuSYpESwpBV08c4#tnO9yvjXkTHox13ouFN1jylo4DAnchac" +
		"nYR6B6DMltaEQNeDZ0vbcZpr92dcK$Hor3dgPaQ5#egr$WCPozvX");

	private final Action[] actions;

	public ShapePropParser() {
		super(PARSING_TABLES);
		actions = new Action[] {
			Action.RETURN,	// [0] $goal = caselist
			new Action() {	// [1] caselist = case.c
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_c = _symbols[offset + 1];
					final SPCase c = (SPCase) _symbol_c.value;
					 return new SPCaselist(c, null);
				}
			},
			new Action() {	// [2] caselist = case.c OROR caselist.l
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_c = _symbols[offset + 1];
					final SPCase c = (SPCase) _symbol_c.value;
					final Symbol _symbol_l = _symbols[offset + 3];
					final SPCaselist l = (SPCaselist) _symbol_l.value;
					 return new SPCaselist(c, l);
				}
			},
			new Action() {	// [3] case = pattern.p ARROW output.o
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_p = _symbols[offset + 1];
					final SPAbstractPattern p = (SPAbstractPattern) _symbol_p.value;
					final Symbol _symbol_o = _symbols[offset + 3];
					final SPOutput o = (SPOutput) _symbol_o.value;
					 return new SPCase(p, o);
				}
			},
			new Action() {	// [4] output = vectorExpr.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final SPAbstractVectorExpr v = (SPAbstractVectorExpr) _symbol_v.value;
					 return new SPOutput(v, null);
				}
			},
			new Action() {	// [5] output = vectorExpr.v COMMA output.o
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final SPAbstractVectorExpr v = (SPAbstractVectorExpr) _symbol_v.value;
					final Symbol _symbol_o = _symbols[offset + 3];
					final SPOutput o = (SPOutput) _symbol_o.value;
					 return new SPOutput(v, o);
				}
			},
			new Action() {	// [6] pattern = patternElement.e
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 1];
					final SPAbstractPattern e = (SPAbstractPattern) _symbol_e.value;
					 return new SPPatternList(e, null);
				}
			},
			new Action() {	// [7] pattern = patternElement.e COMMA pattern.p
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 1];
					final SPAbstractPattern e = (SPAbstractPattern) _symbol_e.value;
					final Symbol _symbol_p = _symbols[offset + 3];
					final SPAbstractPattern p = (SPAbstractPattern) _symbol_p.value;
					 return new SPPatternList(e, p);
				}
			},
			new Action() {	// [8] patternElement = matchExpr.m
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_m = _symbols[offset + 1];
					final SPAbstractMatchExpr m = (SPAbstractMatchExpr) _symbol_m.value;
					 return m;
				}
			},
			new Action() {	// [9] patternElement = assignStmt.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final SPAbstractPattern a = (SPAbstractPattern) _symbol_a.value;
					 return a;
				}
			},
			new Action() {	// [10] patternElement = assertStmt.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final SPAbstractPattern a = (SPAbstractPattern) _symbol_a.value;
					 return a;
				}
			},
			new Action() {	// [11] matchExpr = matchExpr.m OR matchExpr.n
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_m = _symbols[offset + 1];
					final SPAbstractMatchExpr m = (SPAbstractMatchExpr) _symbol_m.value;
					final Symbol _symbol_n = _symbols[offset + 3];
					final SPAbstractMatchExpr n = (SPAbstractMatchExpr) _symbol_n.value;
					 return new SPOr(m,n);
				}
			},
			new Action() {	// [12] matchExpr = LRPAREN pattern.p RRPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_p = _symbols[offset + 2];
					final SPAbstractPattern p = (SPAbstractPattern) _symbol_p.value;
					 return new SPRParen(p);
				}
			},
			new Action() {	// [13] matchExpr = matchExpr.m QUESTION
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_m = _symbols[offset + 1];
					final SPAbstractMatchExpr m = (SPAbstractMatchExpr) _symbol_m.value;
					 return new SPQuestion(m);
				}
			},
			new Action() {	// [14] matchExpr = matchExpr.m MULT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_m = _symbols[offset + 1];
					final SPAbstractMatchExpr m = (SPAbstractMatchExpr) _symbol_m.value;
					 return new SPStar(m);
				}
			},
			new Action() {	// [15] matchExpr = matchExpr.m PLUS
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_m = _symbols[offset + 1];
					final SPAbstractMatchExpr m = (SPAbstractMatchExpr) _symbol_m.value;
					 return new SPPlus(m);
				}
			},
			new Action() {	// [16] matchExpr = vectorExpr.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final SPAbstractVectorExpr v = (SPAbstractVectorExpr) _symbol_v.value;
					 return v;
				}
			},
			new Action() {	// [17] matchExpr = SQUOTATION ID.i SQUOTATION
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					 return new SPStringLiteral(i);
				}
			},
			new Action() {	// [18] matchExpr = SQUOTATION LOWERCASE.i SQUOTATION
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					 return new SPStringLiteral(i);
				}
			},
			Action.NONE,  	// [19] matchExpr = 
			new Action() {	// [20] vectorExpr = SCALAR.d
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_d = _symbols[offset + 1];
					final String d = (String) _symbol_d.value;
					 return new SPScalar(d);
				}
			},
			new Action() {	// [21] vectorExpr = UPPERCASE.u
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_u = _symbols[offset + 1];
					final String u = (String) _symbol_u.value;
					 return new SPUppercase(u);
				}
			},
			new Action() {	// [22] vectorExpr = ANY.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final String a = (String) _symbol_a.value;
					 return new SPAny(a);
				}
			},
			new Action() {	// [23] vectorExpr = LSPAREN RSPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new SPEmptySetMatching();
				}
			},
			new Action() {	// [24] vectorExpr = vertcatExpr.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final SPVertcatExpr v = (SPVertcatExpr) _symbol_v.value;
					 return v;
				}
			},
			new Action() {	// [25] vertcatExpr = LSPAREN vertcatExprArglist.vl RSPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_vl = _symbols[offset + 2];
					final SPVertExprArglist vl = (SPVertExprArglist) _symbol_vl.value;
					 return new SPVertcatExpr(vl);
				}
			},
			new Action() {	// [26] vertcatExprArglist = vertcatExprArg.vt
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_vt = _symbols[offset + 1];
					final SPAbstractVertcatExprArg vt = (SPAbstractVertcatExprArg) _symbol_vt.value;
					 return new SPVertExprArglist(vt, null);
				}
			},
			new Action() {	// [27] vertcatExprArglist = vertcatExprArg.vt COMMA vertcatExprArglist.vtl
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_vt = _symbols[offset + 1];
					final SPAbstractVertcatExprArg vt = (SPAbstractVertcatExprArg) _symbol_vt.value;
					final Symbol _symbol_vtl = _symbols[offset + 3];
					final SPVertExprArglist vtl = (SPVertExprArglist) _symbol_vtl.value;
					 return new SPVertExprArglist(vt, vtl);
				}
			},
			new Action() {	// [28] vertcatExprArg = scalarExpr.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final SPAbstractScalarExpr s = (SPAbstractScalarExpr) _symbol_s.value;
					 return s;
				}
			},
			new Action() {	// [29] vertcatExprArg = vectorExpr.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final SPAbstractVectorExpr v = (SPAbstractVectorExpr) _symbol_v.value;
					 return v;
				}
			},
			new Action() {	// [30] scalarExpr = NUMBER.n
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 1];
					final Number n = (Number) _symbol_n.value;
					 return new SPNumber(n);
				}
			},
			new Action() {	// [31] scalarExpr = LOWERCASE.l
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_l = _symbols[offset + 1];
					final String l = (String) _symbol_l.value;
					 return new SPLowercase(l);
				}
			},
			new Action() {	// [32] assignStmt = AssignmentLhs.l EQUAL AssignmentRhs.r
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_l = _symbols[offset + 1];
					final SPAbstractVertcatExprArg l = (SPAbstractVertcatExprArg) _symbol_l.value;
					final Symbol _symbol_r = _symbols[offset + 3];
					final SPAbstractVertcatExprArg r = (SPAbstractVertcatExprArg) _symbol_r.value;
					 return new SPAssignStmt(l, r);
				}
			},
			new Action() {	// [33] AssignmentLhs = LOWERCASE.l
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_l = _symbols[offset + 1];
					final String l = (String) _symbol_l.value;
					 return new SPLowercase(l);
				}
			},
			new Action() {	// [34] AssignmentLhs = UPPERCASE.u
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_u = _symbols[offset + 1];
					final String u = (String) _symbol_u.value;
					 return new SPUppercase(u);
				}
			},
			new Action() {	// [35] AssignmentLhs = UPPERCASE.u LRPAREN scalarExpr.s RRPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_u = _symbols[offset + 1];
					final String u = (String) _symbol_u.value;
					final Symbol _symbol_s = _symbols[offset + 3];
					final SPAbstractScalarExpr s = (SPAbstractScalarExpr) _symbol_s.value;
					 return new SPUpperIndex(u, s);
				}
			},
			new Action() {	// [36] AssignmentLhs = ANY.a LRPAREN scalarExpr.s RRPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final String a = (String) _symbol_a.value;
					final Symbol _symbol_s = _symbols[offset + 3];
					final SPAbstractScalarExpr s = (SPAbstractScalarExpr) _symbol_s.value;
					 return new SPUpperIndex(a, s);
				}
			},
			new Action() {	// [37] AssignmentRhs = scalarExpr.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final SPAbstractScalarExpr s = (SPAbstractScalarExpr) _symbol_s.value;
					 return s;
				}
			},
			new Action() {	// [38] AssignmentRhs = vectorExpr.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final SPAbstractVectorExpr v = (SPAbstractVectorExpr) _symbol_v.value;
					 return v;
				}
			},
			new Action() {	// [39] AssignmentRhs = fnCall.f
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_f = _symbols[offset + 1];
					final SPAbstractPattern f = (SPAbstractPattern) _symbol_f.value;
					 return f;
				}
			},
			new Action() {	// [40] fnCall = ID.i LRPAREN RRPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					 return new SPFunCall(i, null);
				}
			},
			new Action() {	// [41] fnCall = ID.i LRPAREN arglist.al RRPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_al = _symbols[offset + 3];
					final SPAbstractVertcatExprArg al = (SPAbstractVertcatExprArg) _symbol_al.value;
					 return new SPFunCall(i, al);
				}
			},
			new Action() {	// [42] arglist = arg.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final SPAbstractVertcatExprArg a = (SPAbstractVertcatExprArg) _symbol_a.value;
					 return new SPArglist(a, null);
				}
			},
			new Action() {	// [43] arglist = arg.a COMMA arglist.al
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final SPAbstractVertcatExprArg a = (SPAbstractVertcatExprArg) _symbol_a.value;
					final Symbol _symbol_al = _symbols[offset + 3];
					final SPAbstractVertcatExprArg al = (SPAbstractVertcatExprArg) _symbol_al.value;
					 return new SPArglist(a, al);
				}
			},
			new Action() {	// [44] arg = scalarExpr.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final SPAbstractScalarExpr s = (SPAbstractScalarExpr) _symbol_s.value;
					 return s;
				}
			},
			new Action() {	// [45] arg = vectorExpr.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final SPAbstractVectorExpr v = (SPAbstractVectorExpr) _symbol_v.value;
					 return v;
				}
			},
			new Action() {	// [46] assertStmt = fnCall.f
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_f = _symbols[offset + 1];
					final SPAbstractPattern f = (SPAbstractPattern) _symbol_f.value;
					 return f;
				}
			}
		};
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		return actions[rule_num].reduce(_symbols, offset);
	}
}
