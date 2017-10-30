%{
 #include "y.tab.h"
 extern char yyval;
%}

%%
[0-9]+ {yylval.symbol = (char)yytext[0];return NUMBER;}
[a-zA-Z]+ {yylval.symbol = (char)yytext[0];return LETTER;}

\n {return 0;}
.  {return yytext[0];}
%%

int yywrap()
{
  return 1;
}
