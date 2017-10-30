%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void threeAddress();
void Quadruple();

char AddtoTable(char,char,char);

int index1= 0;
char temp = 'A';
struct expr
{
char operand1;
char operand2;
char operator;
};
%}

%union
{
char symbol;
}

%token <symbol> NUMBER LETTER;
%type <symbol> exp;

%left '-''+'
%right '*''/'

%%

statement : LETTER '=' exp ';' {AddtoTable((char)$1,(char)$3,'=');}
        |exp ';'
        ;
exp: exp '+' exp {$$ = AddtoTable((char)$1,(char)$3,'+');}|
      exp '-' exp {$$ = AddtoTable((char)$1,(char)$3,'-');}|
      exp '*' exp {$$ = AddtoTable((char)$1,(char)$3,'*');}|
      exp '/' exp {$$ = AddtoTable((char)$1,(char)$3,'/');}|
      '('exp')' {$$ = (char)$2;}|
      NUMBER {$$ = (char)$1;}|
      LETTER {$$ = (char)$1;}
      ;
%%

void yyerror(char *s)
{
  printf("%s",s);
}

struct expr arr[20];
int id =0;

char AddtoTable(char operand1, char operand2, char operator)
{
  arr[index1].operand1 = operand1;
  arr[index1].operand2 = operand2;
  arr[index1].operator = operator;
  index1++;
  temp++;
  return temp;
}

void threeAddress()
{
  int cnt=0;
  temp++;
  printf("ThreeAddress code");
  while(cnt<index1)
  {
    printf("%c: =\t",temp);
    if(isalpha(arr[cnt].operand1))
    {
      printf("%c\t",arr[cnt].operand1);
    }
    else
    {
      printf("%c\t",temp);
    }

    printf("%c\t",arr[cnt].operator);

    if(isalpha(arr[cnt].operand2))
    {
      printf("%c\t",arr[cnt].operand2);
    }
    else
    {
      printf("%c\t",temp);
    }
    printf("\n");
    temp++;
    cnt++;

  }

}

int main()
{
  printf("Enter exoression");
  yyparse();
  temp = 'A';
  threeAddress();
}

