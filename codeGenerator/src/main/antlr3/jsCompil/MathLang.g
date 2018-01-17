grammar MathLang;

options {
  language=Java;
  output=AST;

  backtrack=true;
  memoize=true;
}

tokens {
  UNKNOWN                   ;
  DECL      =   'var'       ; //
  IF        =   'if'        ; //
  FOR       =   'for'       ; //
  FUNCTION  =   'function'  ; //
  FUNC_CALL				    ; //
  RETURN    =   'return'    ; //
  WHILE     =   'while'     ; //
  DO        =   'do'	    ; //
  TRUE      =   'true'      ;
  FALSE     =   'false'     ;
  INCR				        ; //
  DECR				        ; //
  ARRAY				        ; //
  ARR_CALL			        ; //
  BLOCK                     ; //
  PARAMS                    ; //
  ARGS				        ; //
}


@parser::header { package jsCompil; }
@lexer::header { package jsCompil; }


WHITESPACE :
    ( '\t' | ' ' | '\r' | '\n'| '\u000C')+    { $channel = HIDDEN; }
;

SL_COMMENT:
  '//' (options { greedy = false; }: .)* '\r'? '\n' { $channel=HIDDEN; }
;

ML_COMMENT:
  '/*' (options { greedy = false; }: .)* '*/' { $channel=HIDDEN; }
;



NUMBER: ('0'..'9')+ ('.' ('0'..'9')+)? //
;
IDENT:  ( 'a'..'z' | 'A'..'Z' | '_' )
        ( 'a'..'z' | 'A'..'Z' | '_' | '0'..'9' )* //
        ( '.'IDENT )?
;

STRING: '"'(.)*'"'; //
ADD:    '+'     ; //
SUB:    '-'     ; //
MUL:    '*'     ; //
DIV:    '/'     ; //
MOD:	'%'		; //

GE:       '>='  ; //
LE:       '<='  ; //
NEQUALS:  '!='  ; //
EQUALS:   '=='  ; //
GT:       '>'   ; //
LT:       '<'   ; //

OR:      '||'   ; //
AND:      '&&'   ; //

ASSIGN: '='     ; //

NOT: '!'		; //

//Math and logic

group: '('! term ')'! | NUMBER | IDENT | function_array_call | bool;
mult: group ( ( MUL | DIV | MOD )^ group )*  ;
add:  ((mult|STRING)  ( ( ADD | SUB )^ (mult|STRING)  )*) | incr | decr  ;
compare: (add ( ( GE | LE | NEQUALS | EQUALS | GT | LT)^ add )?) | not ;
and:  compare (AND^ compare )* ;
or: and (OR^ and )* ;
term: or | function_array_call | STRING | array_decl | func_decl | bool;

not: '!' term -> ^(NOT term);

//Functions

func_params: ( IDENT (',' IDENT)* )? -> ^(PARAMS IDENT*)  ;
func_decl:
  FUNCTION^ IDENT '('! func_params ')'!
  '{'! exprList '}'!
;

//Arrays

arg: term|IDENT;
array_args: (arg (','! arg)* )?;
array_decl:
    '[' array_args ']'-> ^(ARRAY array_args)
;

//Function and array fuse

function_call_args:
    '(' (arg (',' arg)*)? ')' -> ^(FUNC_CALL ^(ARGS arg*))
;
array_call_arg:
    '['arg']' -> ^(ARR_CALL arg)
;
function_array_call_args: function_call_args | array_call_arg;

function_array_call:
	    IDENT (function_array_call_args^)+
;

//Expressions

var_decl: DECL^ IDENT;

incr: (IDENT '++') -> ^(INCR IDENT);
decr: (IDENT '--') -> ^(DECR IDENT);

bool: TRUE | FALSE;

expr1:
  (IDENT | var_decl | function_array_call) ASSIGN^ (term)
| var_decl
| incr | decr
| RETURN^ term?
| function_array_call
;

expr2:
  '{'! exprList '}'!
| DO expr WHILE '(' term ')' -> ^(DO expr term)
| WHILE^ '('! term ')'! expr
| IF^ '('! term ')'! expr ( 'else'! expr )?
| FOR^ '('! expr1 ';'! or ';'! expr1 ')'! expr
| func_decl
;

expr:
  expr1 (';'!)+
| expr2
;

exprList:
  ( expr )* -> ^(BLOCK expr*)
;

result: exprList EOF!;

public execute:
  result
;
