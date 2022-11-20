Rao Oad
PLC MW 5:30 - 7:15

a. Lexemes can be categorized into tokens based on their contents or what they start with.
If they contain a number and a period, we can classify them as a double. If they contain a number
and no period, they can be classified as an integer.

If a lexeme contains an equals sign, we can check the next character to see if it is also an
equals sign. If it is, we can can classify these 2 lexemes as an equivalence operator.

If only one equal sign exists, we can classify the 1 lexeme as an assignment operator.

For this language, an integer token will be returned if and only if a lexeme contains
an integer and no period.

For example:

int a = 0;
int b = 2;
int total = a + b;


In this example, lexemes 0 and 2 are both considered integers because there is no period contained.

If we had the following:

double a = 2.2;

2.2 is now considered a double token instead of an integer token as there is a period present.

b. Variable names must not start with a number or special symbols, capitals, or underscores/whitespace characters.
Selection statements should follow an if-then, if-else, or switch structure for uniform format and continuity purposes.
Keywords that are used include but are not limited to:

	    "import", "class", "while", "if", "else", "public",
            "private", "protected", "switch", "case", "super",
            "static", "implements", "interface", "package", "new",
            "continue", "try", "this", "final", "byte", "int", "char",
            "String", "float", "double", "boolean", "return"
			
We utilize a structuring known as GEMSEA in order to control the ordering in which operators are processed to follow the
mathematical order of operations.

GEMSEA stands for the following:

G: Grouping, includes parentheses/brackets
E: Exponents
M: Multiplication and Division
S: Subtraction and Division
E: Equivalence, when comparing values in a conditional case (etc: if (x == 2))
A: Assignment, after all other operations are performed, the final value is assigned to a variable
   Example:
   int a = 2(3 + 4);
   int a = 2(7);
   int a = 14;
