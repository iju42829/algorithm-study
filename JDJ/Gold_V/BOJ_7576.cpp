#include <iostream>
#include <queue>

using namespace std;

int xLastIndex, yLastIndex;
bool** doned;
int day=-1;
int tomatoCount;
//pair<int,int> = (y,x);
queue<pair<int, int>> tomatoQue;
void (*checkFunction[4])(pair<int, int>);

void init();
bool isPassOneDay();
void checkUpAndPushQue(pair<int, int> loc);
void checkLeftAndPushQue(pair<int,int> loc);
void checkRightAndPushQue(pair<int,int> loc);
void checkDownAndPushQue(pair<int,int> loc);

//if can go this location, return true
bool checkoutBoundaryAndDonedMap(pair<int, int>loc);

void showMap();



int main() {
	init();

	while (isPassOneDay()) {
	}
	if (tomatoCount != 0) {
		 cout << -1;
	}
	else {
		cout << day;
	}
}

void init() {
	//get x,y;
	int m, n;
	cin >> m >> n;
	xLastIndex = m-1;
	yLastIndex = n-1;

	checkFunction[0] = checkUpAndPushQue;
	checkFunction[1] = checkLeftAndPushQue;
	checkFunction[2] = checkRightAndPushQue;
	checkFunction[3] = checkDownAndPushQue;

	//init Y
	doned = new bool* [n];


	//init X
	for (int i = 0; i < n;i++) {
		doned[i] = new bool[m];
	}

	int data;
	//allocate value, i == y, j == x
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> data;
			if (data == -1) {
				doned[i][j] = true;
			}
			else if(data == 1) {
				doned[i][j] = true;
				tomatoQue.push(pair<int, int>(i, j));
			}
			else {
				doned[i][j] = false;
				tomatoCount++;
			}
		}
	}
}

bool isPassOneDay() {
	//get count of ripe fruit
	int ripeTomatoCount = tomatoQue.size();

	if (ripeTomatoCount == 0) {
		return false;
	}

	//loop of ripe fruit count
	for (int i = 0; i < ripeTomatoCount; i++) {
		pair<int, int> loc = tomatoQue.front();
		tomatoQue.pop();

		for (int i = 0; i < 4; i++) {
			checkFunction[i](loc);
		}
	}

	day++;

	return true;
}

void checkUpAndPushQue(pair<int, int> loc){
	int y = loc.first - 1;
	int x = loc.second;

	if (checkoutBoundaryAndDonedMap(pair<int, int>(y, x))) {
		doned[y][x] = true;
		tomatoQue.push(pair<int, int>(y, x));
		tomatoCount--;
	}
}
void checkLeftAndPushQue(pair<int, int> loc){
	int y = loc.first;
	int x = loc.second-1;

	if (checkoutBoundaryAndDonedMap(pair<int, int>(y, x))) {
		doned[y][x] = true;
		tomatoQue.push(pair<int, int>(y, x));
		tomatoCount--;
	}
}
void checkRightAndPushQue(pair<int, int> loc){
	int y = loc.first;
	int x = loc.second +1 ;

	if (checkoutBoundaryAndDonedMap(pair<int, int>(y, x))) {
		doned[y][x] = true;
		tomatoQue.push(pair<int, int>(y, x));
		tomatoCount--;
	}
}
void checkDownAndPushQue(pair<int, int> loc){
	int y = loc.first+1;
	int x = loc.second;

	if (checkoutBoundaryAndDonedMap(pair<int, int>(y, x))) {
		doned[y][x] = true;
		tomatoQue.push(pair<int, int>(y, x));
		tomatoCount--;
	}
}

bool checkoutBoundaryAndDonedMap(pair<int, int> loc)
{
	int y = loc.first;
	int x = loc.second;

	//Boundary
	if (y < 0 || yLastIndex < y) return false;
	if (x < 0 || xLastIndex < x) return false;

	//Doned
	if (doned[y][x]) return false;

	return true;
}

void showMap(){
	for (int i = 0; i <= yLastIndex; i++) {
		for (int j = 0; j <= xLastIndex; j++) {
			if (doned[i][j]) cout << "1 ";
			else cout << "0 ";
		}
		cout << endl;
	}
}

