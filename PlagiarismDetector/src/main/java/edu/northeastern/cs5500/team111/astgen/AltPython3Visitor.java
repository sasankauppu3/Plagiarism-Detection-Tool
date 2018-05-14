package edu.northeastern.cs5500.team111.astgen;// Generated from AltPython3.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AltPython3Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AltPython3Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#single_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_input(AltPython3Parser.Single_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#file_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_input(AltPython3Parser.File_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#eval_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_input(AltPython3Parser.Eval_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#decorator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorator(AltPython3Parser.DecoratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#decorators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorators(AltPython3Parser.DecoratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#decorated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorated(AltPython3Parser.DecoratedContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(AltPython3Parser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(AltPython3Parser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#typedargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedargslist(AltPython3Parser.TypedargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#tfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTfpdef(AltPython3Parser.TfpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#varargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarargslist(AltPython3Parser.VarargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#vfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVfpdef(AltPython3Parser.VfpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(AltPython3Parser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(AltPython3Parser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmall_stmt(AltPython3Parser.Small_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#print_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_stmt(AltPython3Parser.Print_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_stmt(AltPython3Parser.Expr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#testlist_star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_star_expr(AltPython3Parser.Testlist_star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugassign(AltPython3Parser.AugassignContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#del_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDel_stmt(AltPython3Parser.Del_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#pass_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPass_stmt(AltPython3Parser.Pass_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#flow_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow_stmt(AltPython3Parser.Flow_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#break_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_stmt(AltPython3Parser.Break_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#continue_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_stmt(AltPython3Parser.Continue_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(AltPython3Parser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#yield_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_stmt(AltPython3Parser.Yield_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#raise_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaise_stmt(AltPython3Parser.Raise_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#import_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_stmt(AltPython3Parser.Import_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#import_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_name(AltPython3Parser.Import_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#import_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_from(AltPython3Parser.Import_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#import_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_name(AltPython3Parser.Import_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#dotted_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_name(AltPython3Parser.Dotted_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#import_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_names(AltPython3Parser.Import_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#dotted_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_names(AltPython3Parser.Dotted_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#dotted_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_name(AltPython3Parser.Dotted_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#global_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_stmt(AltPython3Parser.Global_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#nonlocal_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonlocal_stmt(AltPython3Parser.Nonlocal_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#assert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssert_stmt(AltPython3Parser.Assert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_stmt(AltPython3Parser.Compound_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(AltPython3Parser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(AltPython3Parser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(AltPython3Parser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#try_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTry_stmt(AltPython3Parser.Try_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#with_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_stmt(AltPython3Parser.With_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#with_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_item(AltPython3Parser.With_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#except_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExcept_clause(AltPython3Parser.Except_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(AltPython3Parser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(AltPython3Parser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#test_nocond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_nocond(AltPython3Parser.Test_nocondContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#lambdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdef(AltPython3Parser.LambdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#lambdef_nocond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdef_nocond(AltPython3Parser.Lambdef_nocondContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#or_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_test(AltPython3Parser.Or_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#and_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_test(AltPython3Parser.And_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#not_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_test(AltPython3Parser.Not_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(AltPython3Parser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_op(AltPython3Parser.Comp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar_expr(AltPython3Parser.Star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(AltPython3Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#xor_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXor_expr(AltPython3Parser.Xor_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#and_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expr(AltPython3Parser.And_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#shift_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift_expr(AltPython3Parser.Shift_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#arith_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArith_expr(AltPython3Parser.Arith_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(AltPython3Parser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(AltPython3Parser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(AltPython3Parser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(AltPython3Parser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#testlist_comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_comp(AltPython3Parser.Testlist_compContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailer(AltPython3Parser.TrailerContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#subscriptlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptlist(AltPython3Parser.SubscriptlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(AltPython3Parser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#sliceop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceop(AltPython3Parser.SliceopContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#exprlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprlist(AltPython3Parser.ExprlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#testlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist(AltPython3Parser.TestlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#dictorsetmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictorsetmaker(AltPython3Parser.DictorsetmakerContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(AltPython3Parser.ClassdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(AltPython3Parser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(AltPython3Parser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#comp_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_iter(AltPython3Parser.Comp_iterContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#comp_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_for(AltPython3Parser.Comp_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#comp_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_if(AltPython3Parser.Comp_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#yield_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_expr(AltPython3Parser.Yield_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#yield_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_arg(AltPython3Parser.Yield_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(AltPython3Parser.StrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(AltPython3Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link AltPython3Parser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(AltPython3Parser.IntegerContext ctx);
}