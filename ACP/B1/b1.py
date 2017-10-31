import json
def printboard(board):
	for i in range(8):
		for j in range(8):
			print board[i][j],
		print

def isSafe(board, row, col):
	for i in range(8):
		if board[row][i]==1:
			return False

	for i in range(8):
		for j in range(8):
			if board[i][j]==1:
				if abs(col-j)==abs(row-i):
					return False

	return True

def solveQueen(board, col):
	if col>=8:
		printboard(board)
		print
		return True		
	for i in range(8):
		if isSafe(board, i, col)==True:
			board[i][col]=1
			solveQueen(board, col+1)
			board[i][col]=0



def solve():	
	board = [[0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0],
		[0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0],
              [0, 0, 0, 0, 0, 0, 0, 0]
		]
	data=[]
	with open('input.json') as f:
		data=json.load(f)
	if data["start"]<0 or data["start"]>7:
		print "Invalid input"
		exit()
	board[data["start"]][0]=1;
	if solveQueen(board, 1)==False:
		print "No solution exists"
		return
	printboard(board)
	return


solve()
