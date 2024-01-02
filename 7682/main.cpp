#include <iostream>
#include <string>
using namespace std;
char** makeMatrix(string line);
int getDoneCount(char** matrix);
bool isVaildCount(string line);
bool isFullMatrix(string line);


int main() {
	string line;
	do {
		getline(cin, line);
		if (line == "end") break;

		char** matrix = makeMatrix(line);

		if (!isVaildCount(line)) printf("invalid\n");
		else if(isFullMatrix(line))printf("valid\n");
		else if(getDoneCount(matrix)==0)printf("invalid\n");
		else printf("valid\n");

		free(matrix);

	} while (true);

}


char** makeMatrix(string line)
{
	int lineCount = 0;

	char** matrix = new char* [3];

	for (int i = 0; i < 3; i++) {
		matrix[i] = new char[3];
	}

	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			matrix[i][j] = line[lineCount];
			lineCount++;
		}
	}
	
	return matrix;
}

int getDoneCount(char** matrix) {
	int doneCount = 0;

	for (int raw = 0; raw < 3; raw++){
		if (matrix[raw][0] == matrix[raw][1] && matrix[raw][1] == matrix[raw][2] && matrix[raw][0] != '.') {
			doneCount++;
		}
	}//colunm check

	for (int col = 0; col < 3; col++) {
		if (matrix[col][0] == matrix[col][1] && matrix[col][1] == matrix[col][2] && matrix[col][0] != '.') {
			doneCount++;
		}
	}//raw check

	if (matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2] && matrix[0][0] != '.') {
		doneCount++;
	}
	if (matrix[2][0] == matrix[1][1] && matrix[1][1] == matrix[0][2] && matrix[2][0] != '.') {
		doneCount++;
	}

	return doneCount;
}

bool isVaildCount(string line) {
	int xCount=0;
	int oCount = 0;

	for (int i = 0; i < 9; i++) {
		switch (line[i])
		{
		case 'O':
			oCount++;
			break;
		case 'X':
			xCount++;
			break;
		case '.':
			break;
		}
	}

	int diff = xCount - oCount;

	if (0 == diff || diff == 1) {
		return true;
	}
	else return false;
}

bool isFullMatrix(string line) {
	for (int i = 0; i < 9; i++)
	{
		if (line[i] == 'O' || line[i] == 'X') continue;
		return false;
	}

	return true;
}