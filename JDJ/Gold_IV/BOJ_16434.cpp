#include <iostream>
#include <vector>

using namespace std;

long long roomCount, attackPower;
long long answer;

vector<pair<int, pair<long long, long long>>> dungeon;


bool enterDungeon(long long hp) {

    long long currAtk = attackPower;
    long long currHp = hp;

    for (int i = 0; i < roomCount; i++) {

        int state = dungeon[i].first;

        // 몬스터
        if (state == 1) {
            long long monsterAttack = dungeon[i].second.first;
            long long monsterHp = dungeon[i].second.second;

            long long turns;
            if (monsterHp % currAtk == 0) turns = monsterHp / currAtk - 1;
            else turns = monsterHp / currAtk;

            currHp -= monsterAttack * turns;
            if (currHp <= 0) return false;
        }
        // 포션
        else {
            currAtk += dungeon[i].second.first;
            currHp = min(hp, currHp + dungeon[i].second.second);
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
