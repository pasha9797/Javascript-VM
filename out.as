	//Array declaration begin
0: declArray 6604_tmp
	//Insert value 0 begin
	//Array declaration begin
1: declArray 1144_tmp
	//Insert value 0 begin
2: push 1
3: push 0
4: pushFromVar 1144_tmp
5: popToArray
	//Insert value 1 begin
6: push 2
7: push 1
8: pushFromVar 1144_tmp
9: popToArray
	//Array filled, pushing it to stack
10: pushFromVar 1144_tmp
	//Array declaration end
11: push 0
12: pushFromVar 6604_tmp
13: popToArray
	//Insert value 1 begin
	//Array declaration begin
14: declArray -6855_tmp
	//Insert value 0 begin
15: push 3
16: push 0
17: pushFromVar -6855_tmp
18: popToArray
	//Insert value 1 begin
19: push 4
20: push 1
21: pushFromVar -6855_tmp
22: popToArray
	//Array filled, pushing it to stack
23: pushFromVar -6855_tmp
	//Array declaration end
24: push 1
25: pushFromVar 6604_tmp
26: popToArray
	//Array filled, pushing it to stack
27: pushFromVar 6604_tmp
	//Array declaration end
28: pop 2441_matr1
	//Array declaration begin
29: declArray 4302_tmp
	//Insert value 0 begin
	//Array declaration begin
30: declArray 3776_tmp
	//Insert value 0 begin
31: push 3
32: push 0
33: pushFromVar 3776_tmp
34: popToArray
	//Insert value 1 begin
35: push 2
36: push 1
37: pushFromVar 3776_tmp
38: popToArray
	//Array filled, pushing it to stack
39: pushFromVar 3776_tmp
	//Array declaration end
40: push 0
41: pushFromVar 4302_tmp
42: popToArray
	//Insert value 1 begin
	//Array declaration begin
43: declArray -415_tmp
	//Insert value 0 begin
44: push 1
45: push 0
46: pushFromVar -415_tmp
47: popToArray
	//Insert value 1 begin
48: push 4
49: push 1
50: pushFromVar -415_tmp
51: popToArray
	//Array filled, pushing it to stack
52: pushFromVar -415_tmp
	//Array declaration end
53: push 1
54: pushFromVar 4302_tmp
55: popToArray
	//Array filled, pushing it to stack
56: pushFromVar 4302_tmp
	//Array declaration end
57: pop 2441_matr2
	//System call begin
58: pushFromVar 2441_matr1
59: systemCall alert
	//System call end
	//System call begin
60: pushFromVar 2441_matr2
61: systemCall alert
	//System call end
	//Array declaration begin
62: declArray 707_tmp
	//Insert value 0 begin
	//Array declaration begin
63: declArray -1883_tmp
	//Insert value 0 begin
64: push 0
65: push 0
66: pushFromVar -1883_tmp
67: popToArray
	//Insert value 1 begin
68: push 0
69: push 1
70: pushFromVar -1883_tmp
71: popToArray
	//Array filled, pushing it to stack
72: pushFromVar -1883_tmp
	//Array declaration end
73: push 0
74: pushFromVar 707_tmp
75: popToArray
	//Insert value 1 begin
	//Array declaration begin
76: declArray -6473_tmp
	//Insert value 0 begin
77: push 0
78: push 0
79: pushFromVar -6473_tmp
80: popToArray
	//Insert value 1 begin
81: push 0
82: push 1
83: pushFromVar -6473_tmp
84: popToArray
	//Array filled, pushing it to stack
85: pushFromVar -6473_tmp
	//Array declaration end
86: push 1
87: pushFromVar 707_tmp
88: popToArray
	//Array filled, pushing it to stack
89: pushFromVar 707_tmp
	//Array declaration end
90: pop 2441_matr3
	//For loop begin
91: push 0
92: pop 240_i
93: pushFromVar 240_i
94: push 2
95: lt
96: jumpFalse 149
	//For loop begin
97: push 0
98: pop 8611_j
99: pushFromVar 8611_j
100: push 2
101: lt
102: jumpFalse 143
	//For loop begin
103: push 0
104: pop 2342_k
105: pushFromVar 2342_k
106: push 2
107: lt
108: jumpFalse 137
	//Array call begin
109: pushFromVar 8611_j
	//Array call begin
110: pushFromVar 240_i
111: pushFromVar 2441_matr3
112: pushFromArray
	//Array call end
113: pushFromArray
	//Array call end
	//Array call begin
114: pushFromVar 2342_k
	//Array call begin
115: pushFromVar 240_i
116: pushFromVar 2441_matr1
117: pushFromArray
	//Array call end
118: pushFromArray
	//Array call end
	//Array call begin
119: pushFromVar 8611_j
	//Array call begin
120: pushFromVar 2342_k
121: pushFromVar 2441_matr2
122: pushFromArray
	//Array call end
123: pushFromArray
	//Array call end
124: mul
125: add
126: pushFromVar 8611_j
	//Array call begin
127: pushFromVar 240_i
128: pushFromVar 2441_matr3
129: pushFromArray
	//Array call end
130: popToArray
131: pushFromVar 2342_k
132: pushFromVar 2342_k
133: push 1
134: add
135: pop 2342_k
136: jump 105
	//For loop end
137: pushFromVar 8611_j
138: pushFromVar 8611_j
139: push 1
140: add
141: pop 8611_j
142: jump 99
	//For loop end
143: pushFromVar 240_i
144: pushFromVar 240_i
145: push 1
146: add
147: pop 240_i
148: jump 93
	//For loop end
	//System call begin
149: pushFromVar 2441_matr3
150: systemCall alert
	//System call end
