#include <iostream>

using namespace std;

class PriorityHeap {
	int lastIndex = 0;
	int* arr;

	void swap(int& data1, int& data2) {
		int temp = data1;
		data1 = data2;
		data2 = temp;
	}
	bool isBigger(int less, int bigger) {
		if (less < bigger) return true;
		else return false;
	}
public:
	PriorityHeap(int size) {
		arr = new int[size + 1];
		arr[0] = -1;
	}

	void insert(int data) {
		arr[++lastIndex] = data;

		for (int i = lastIndex; 1 < i; i = i / 2) {
			int parent = i / 2;

			if (isBigger(arr[i], arr[parent])) {
				swap(arr[i], arr[parent]);
			}
			else break;
		}
	}

	int pop() {
		int returnData = arr[1];
		arr[1] = arr[lastIndex--];

		for (int i = 1; i <= lastIndex / 2;) {
			int leftChildIndex = i * 2;
			int rightChildIndex = i * 2 + 1;

			if (lastIndex < rightChildIndex) {
				if (isBigger(arr[leftChildIndex], arr[i])) {
					swap(arr[leftChildIndex], arr[i]);
					i = leftChildIndex;
				}
				else break;
			}
			else if (rightChildIndex <= lastIndex) {
				int smallerIndex;

				if (isBigger(arr[leftChildIndex], arr[rightChildIndex])) {
					smallerIndex = leftChildIndex;
				}
				else {
					smallerIndex = rightChildIndex;
				}

				if (isBigger(arr[smallerIndex], arr[i])) {
					swap(arr[smallerIndex], arr[i]);
					i = smallerIndex;
				}
				else break;
			}
		}


		return returnData;
	}

	bool isEmpty() {
		return lastIndex == 0;
	}

	int getSize() {
		return lastIndex;
	}
};

int main() {
	int size;
	int data;
	int totalComp = 0;
	cin >> size;
	PriorityHeap h1(size);

	for (int i = 0; i < size; i++) {
		cin >> data;
		h1.insert(data);
	}


	while (h1.getSize() > 1) {
		int comp = h1.pop() + h1.pop();
		h1.insert(comp);
		totalComp += comp;
	}

	cout << totalComp;
}