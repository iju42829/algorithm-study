#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<vector<int>> map;
int mapSize;

struct Location {
	int raw;
	int col;
	int time = 0;
	Location() {
		raw = -1;
		col = -1;
	}
	Location(int r, int c) {
		this->raw = r;
		this->col = c;
	}
	Location(int r, int c, int t) {
		this->raw = r;
		this->col = c;
		this->time = t;
	}
};

class Shark {
	int size;
	int saturation;
	int time;
	Location loc;
public:
	Shark() {
		size = 2;
		saturation = 0;
		time = 0;
	}

	void getPrey() {
		saturation++;
		if (saturation == size) {
			size++;
			saturation = 0;
		}
	}

	const int getSize() {
		return size;
	}

	void addTime(int t) {
		time += t;
	}

	const int getTime() {
		return time;
	}

	void initLocation(Location loc) {
		this->loc = loc;
	}

	void setLocation(Location loc) {
		map[this->loc.raw][this->loc.col] = 0;
		this->loc = loc;
		map[this->loc.raw][this->loc.col] = 9;
	}

	Location getLocation() {
		return this->loc;
	}
};


class BFS {

	Shark s1;

public:
	BFS() {
		mapSize = mapSize - 1;
		int fishSize;

		map = vector<vector<int>>(mapSize + 1, vector<int>(mapSize + 1, 0));

		for (int i = 0; i <= mapSize; i++) {
			for (int j = 0; j <= mapSize; j++) {
				cin >> fishSize;

				if (fishSize == 9) {
					s1.initLocation(Location(i, j));
				}

				map[i][j] = fishSize;
			}
		}
	}

	bool isPass(int raw, int col, int sharkSize,vector<vector<bool>> mat) {
		if (raw > mapSize || raw < 0 || col > mapSize || col < 0) // Out of Map
		{
			return false;
		}

		if (sharkSize < map[raw][col]) {/* Out of Size*/
			return false;
		}

		if (mat[raw][col]) { /*visited*/
			return false;
		}
		return true;
	}

	bool findNode() {
		vector<vector<bool>> visited(mapSize + 1, vector<bool>(mapSize + 1, false));
		queue<Location> bfsQue;
		Location temp = s1.getLocation();
		temp.time = 0;
		bfsQue.push(temp);
		int sharkSize = s1.getSize();
		do {
			Location loc = bfsQue.front();
			bfsQue.pop();
			visited[loc.raw][loc.col] = true;

			//Prey part
			if (map[loc.raw][loc.col] != 0 && map[loc.raw][loc.col] < sharkSize) {
				s1.getPrey();
				s1.addTime(loc.time);
				s1.setLocation(loc);
				return true;
			}

			int nextTime = loc.time + 1;
			//InsertNode part
			if (isPass(loc.raw - 1, loc.col, sharkSize,visited)) {
				bfsQue.push(Location(loc.raw - 1, loc.col, nextTime));
			}
			if (isPass(loc.raw, loc.col + 1, sharkSize,visited)) {
				bfsQue.push(Location(loc.raw, loc.col + 1, nextTime));
			}
			if (isPass(loc.raw, loc.col - 1, sharkSize, visited)) {
				bfsQue.push(Location(loc.raw, loc.col - 1, nextTime));
			}
			if (isPass(loc.raw + 1, loc.col, sharkSize,visited)){
				bfsQue.push(Location(loc.raw + 1, loc.col, nextTime));
			}
			

		} while (bfsQue.size() != 0);

		return false;
	}

	int getTime() {
		return s1.getTime();
	}
};




int main() {
	cin >> mapSize;
	BFS b1;

	while (b1.findNode()) {
		
	}
	cout << b1.getTime() << endl;
	
	
}