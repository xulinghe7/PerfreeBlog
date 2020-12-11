package com.perfree.directive;

import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import com.perfree.common.MarkdownUtil;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.stereotype.Component;

@TemplateDirective("mdToHtml")
@Component
public class MdToHtmlDirective extends BaseDirective {
    public void setExprList(ExprList exprList) {
        super.setExprList(exprList);
    }
    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        String mdStr = getParam(0, scope).toString();
        write(writer, MarkdownUtil.mdToHtml(mdStr));
    }
}
