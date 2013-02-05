package mclint.refactoring;

import mclint.McLintTestCase;
import ast.ASTNode;
import ast.AssignStmt;
import ast.Function;
import ast.FunctionList;
import ast.Name;
import ast.NameExpr;
import ast.ParameterizedExpr;
import ast.Script;

public class RenameVariableTest extends McLintTestCase {
  private void rename(Name node, String newName) {
    RenameVariable.exec(node, newName, kit.getUseDefDefUseChain());
  }
  
  private void assertRenameFails(ASTNode<?> tree, Name name, String newName) {
    ASTNode<?> copy = tree.fullCopy();
    try {
      rename(name, newName);
      fail(String.format("Expected renaming of %s to %s to fail", name.getID(), newName));
    } catch (RenameVariable.NameConflict e) {}
    assertEquals(copy.getPrettyPrinted(), tree.getPrettyPrinted());
  }

  public void testRenameUse() {
    parse("x = 0; y = x;");
    Script script = (Script) kit.getAST();
    Name x = ((NameExpr) ((AssignStmt) script.getStmt(1)).getRHS()).getName();
    
    rename(x, "z");
    
    assertEquivalent("z = 0; y = z;", script);
  }
  
  public void testRenameDef() {
    parse("x = 0; y = x;");
    Script script = (Script) kit.getAST();
    Name x = ((NameExpr) ((AssignStmt) script.getStmt(0)).getLHS()).getName();
    
    rename(x, "z");
    
    assertEquivalent("z = 0; y = z;", script);
  }
  
  public void testRenameArrayGet() {
    parse("x = [1,2]; y = x(1);");
    Script script = (Script) kit.getAST();
    Name x = ((NameExpr) ((ParameterizedExpr) 
        ((AssignStmt) script.getStmt(1)).getRHS()).getTarget()).getName();
    
    rename(x, "z");
    
    assertEquivalent("z = [1,2]; y = z(1);", script);
  }
  
  public void testRenameArraySet() {
    parse("x(2) = 4; y = x(1);");
    Script script = (Script) kit.getAST();
    Name x = ((NameExpr) ((ParameterizedExpr) 
        ((AssignStmt) script.getStmt(1)).getRHS()).getTarget()).getName();

    rename(x, "z");
    
    assertEquivalent("z(2) = 4; y = z(1);", script);
  }
  
  public void testRenameWithConflict() {
    parse("x = 0; y = x;");
    Script script = (Script) kit.getAST();
    Name x = ((NameExpr) ((AssignStmt) script.getStmt(1)).getRHS()).getName();
    
    assertRenameFails(script, x, "y");
  }
  
  public void testRenameWithInputParamConflict() {
    parse(new StringBuilder()
        .append("function x(in)\n")
        .append("x = 0; y = x;\n")
        .append("end").toString());
    Function function = ((FunctionList) kit.getAST()).getFunction(0);
    Name x = ((NameExpr) ((AssignStmt) function.getStmt(1)).getRHS()).getName();
    
    assertRenameFails(function, x, "in");
  }
  
  public void testRenameWithOutputParamConflict() {
    parse(new StringBuilder()
        .append("function out = x(i)\n")
        .append("x = 0; y = x;\n")
        .append("end").toString());
    Function function = ((FunctionList) kit.getAST()).getFunction(0);
    Name x = ((NameExpr) ((AssignStmt) function.getStmt(1)).getRHS()).getName();
    
    assertRenameFails(function, x, "out");
  }
  
  public void testRenameMultipleAssignment() {
    parse("[y, x] = f(); y = x;");
    Script script = (Script) kit.getAST();
    Name x = ((NameExpr) ((AssignStmt) script.getStmt(1)).getRHS()).getName();
    
    rename(x, "z");
    
    assertEquivalent("[y, z] = f(); y = z;", script);
  }
}
