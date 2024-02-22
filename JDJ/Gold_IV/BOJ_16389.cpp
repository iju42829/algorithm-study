#include <iostream>
#include <queue>
using namespace std;

class edge {
public:
	int i, j;
	long long cost;
	edge(int i, int j, long long cost) {
		this->i = i;
		this->j = j;
		this->cost = cost;
	}

	bool operator<(const edge& other) const {
		return cost > other.cost;
	}
};

priority_queue<edge> pq;

class Graph {
	int* root;
	int* height;
	long long cost;
	int edgeCount;
	int size;
public:
	Graph(int size) {
		this->size = size;
		root = new int[size];
		height = new int[size];
		cost = 0;
		edgeCount = 0;

		//make set
		for (int i = 0; i < size; i++) {
			root[i] = i;
			height[i] = 0;
		}
	}

	void unionSet(int i, int j) {
		int iRoot = findRoot(i);
		int jRoot = findRoot(j);
		if (iRoot != jRoot) {

			//regulating rank
			if (height[iRoot] < height[jRoot]) {
				root[iRoot] = jRoot;
			}
			else if (height[iRoot] > height[jRoot]) {
				root[jRoot] = iRoot;
			}
			else {
				root[jRoot] = iRoot;
				height[iRoot]++;
			}
			edgeCount++;
		}
	}

	int findRoot(int i) {
		if (root[i] == i) {
			return i;
		}
		else {//path compression
			return root[i] = findRoot(root[i]);
		}
	}

	long long getMinCost() {
		while (edgeCount < size - 1) {
			edge temp = pq.top();
			pq.pop();
			int lastEdgeCount = edgeCount;
			unionSet(temp.i, temp.j);
			if (lastEdgeCount != edgeCount) {
				this->cost += temp.cost;
			}
		}

		return cost;
	}
};


int main() {
	int size;
	long long cost;
	cin >> size;
	Graph g(size);

	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			cin >> cost;
			if (i < j) {
				pq.emplace(i, j, cost);
			}
		}
	}

	cout<<g.getMinCost();
}