#include <string>
#include <iostream>

using namespace std;

char** makeMatrix(string line);
bool isXWin(char** matrix);
bool isOWin(char** matrix);
int getDiff(string line);
bool isValid(char** mat,string line);
bool isFullMatrix(string line);

int main(){
	char** mat;
	string line;
	

	do {
		getline(cin, line);
		if (line == "end") break;
		mat = makeMatrix(line);

		if (isValid(mat, line))cout << "valid\n";
		else cout << "invalid\n";

		for (int i = 0; i < 3; i++) {
			delete[] mat[i];
		}
		delete[] mat;

	} while (true);

	return 0;
}

char** makeMatrix(string line) {
	int lineCount = 0;
	char** mat = new char* [3];
	for (int i = 0; i < 3; i++) {
		mat[i] = new char[3];
	}

	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			mat[i][j] = line[lineCount++];
		}
	}

	return mat;
}
bool isXWin(char** matrix) {
	for (int raw = 0; raw < 3; raw++) {//check colunm
		if (matrix[raw][0] == 'X' &&
			matrix[raw][0] == matrix[raw][1] &&
			matrix[raw][1] == matrix[raw][2]) {
			return true;
		}
	}

	for (int col = 0; col < 3; col++) {//check raw
		if (matrix[0][col] == 'X' &&
			matrix[0][col] == matrix[1][col] && matrix[1][col] == matrix[2][col])return true;

	}

	/* check cross */ {
		if (matrix[0][0] == 'X' &&
			matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) return true;

		if (matrix[0][2] == 'X' &&
			matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]) return true;
	}

	return false;
}
bool isOWin(char** matrix) {
	for (int raw = 0; raw < 3; raw++) {//check colunm
		if (matrix[raw][0] == 'O' &&
			matrix[raw][0] == matrix[raw][1] &&
			matrix[raw][1] == matrix[raw][2]) {
			return true;
		}
	}

	for (int col = 0; col < 3; col++) {//check raw
		if (matrix[0][col] == 'O' &&
			matrix[0][col] == matrix[1][col] && matrix[1][col] == matrix[2][col])return true;

	}

	/* check cross */ {
		if (matrix[0][0] == 'O' &&
			matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) return true;

		if (matrix[0][2] == 'O' &&
			matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]) return true;
	}

	return false;

}
int getDiff(string line) {
	int xCount = 0;
	int oCount = 0;
	for (int i = 0; i < 9; i++) {
		if (line[i] == 'X') xCount++;
		else if (line[i] == 'O')oCount++;
	}
	return xCount - oCount;
}
bool isValid(char** mat, string line) {
	int diff = getDiff(line);

	if (isXWin(mat) && isOWin(mat))return false;
	if (isXWin(mat)&& diff == 1) return true;
	if (isOWin(mat)&& diff == 0) return true;
	if (isFullMatrix(line)&&diff==1&&!isXWin(mat)&&!isOWin(mat)) return true;

	return false;
}
bool isFullMatrix(string line) {
	for (int i = 0; i < 9; i++) {
		if (line[i] == '.')return false;
	}
	return true;
}