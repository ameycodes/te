%{
/*
*Program to generate tokens 
*/
#include<stdio.h>
#include<string.h>

%}

%%
[\t ]+  			/* ignore whitespace */
[\n ]                           /* ignore newine */
[a-zA-z]+ 			{printf("\n%s",yytext);}
['{','}''(',')',.,",;]          {printf("\n%s",yytext);}
%%

int main()
{
	yyin= fopen("file1.txt","r");
	printf("\nTOKENS:\n");
	yylex();
	printf("\n");	
}

int yywrap()
{

}
