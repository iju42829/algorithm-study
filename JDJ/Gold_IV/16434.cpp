#include <iostream>
#include <vector>

using namespace std;

long long roomCount, attackPower;
long long answer;

vector<pair<int, pair<long long, long long>>> dungeon;


bool enterDungeon(long long healthPoints) {

    long long currentAttack = attackPower;
    long long currentHealth = healthPoints;

    for (int i = 0; i < roomCount; i++) {

        int state = dungeon[i].first;

        // 몬스터
        if (state == 1) {
            long long monsterAttack = dungeon[i].second.first;
            long long monsterHealth = dungeon[i].second.second;

            long long turns;
            if (monsterHealth % currentAttack == 0) turns = monsterHealth / currentAttack - 1;
            else turns = monsterHealth / currentAttack;

            currentHealth -= monsterAttack * turns;
            if (currentHealth <= 0) return false;
        }
        // 포션
        else {
            currentAttack += dungeon[i].second.first;
            currentHealth = min(healthPoints, currentHealth + dungeon[i].second.second);
        }
    }
    return true;
}

int main(void) {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> roomCount >> attackPower;

    int type, value1, value2;
    for (int i = 0; i < roomCount; i++) {
        cin >> type >> value1 >> value2;
        dungeon.push_back({ type, {value1, value2} });
    }

    long long left = 1;
    long long right = 1e18;
    long long mid;

    while (left <= right) {
        mid = (left + right) / 2;

        if (enterDungeon(mid)) {
            right = mid - 1;
            answer = mid;
        }
        else {
            left = mid + 1;
        }
    }

    cout << answer << endl;
}
