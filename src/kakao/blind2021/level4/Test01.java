package kakao.blind2021.level4;
import java.util.*;
public class Test01 {
    static class MemberInfo {
        int memberNo;
        int sale;

        public MemberInfo(int memberNo, int sale) {
            this.memberNo = memberNo;
            this.sale = sale;
        }
    }

    public static int[][] LINKS;
    public static int[] SALES;
    public static int MIN = Integer.MAX_VALUE;
    public static Map<Integer, List<MemberInfo>> TEAM_MAP = new HashMap<>();

    public static int solution(int[] sales, int[][] links) {
        LINKS = links;
        SALES = sales;
        createTeamMap();

        // 매출순으로 정렬
        for (int key : TEAM_MAP.keySet()) {
            List<MemberInfo> memberList = TEAM_MAP.get(key);
            memberList.sort(Comparator.comparingInt(o -> o.sale));
            TEAM_MAP.put(key, memberList);
        }

        int index = 0;
        int[] items = new int[TEAM_MAP.size()];
        for (Map.Entry<Integer, List<MemberInfo>> entry : TEAM_MAP.entrySet()) {
            items[index] = entry.getValue().size();
            index++;
        }
        int[] buckets = new int[items.length];
        combination(items, buckets, buckets.length);
        return MIN;
    }

    public static void createTeamMap() {
        for (int i = 0; i < LINKS.length; i++) {
            int[] cur = LINKS[i];
            int leader = cur[0];
            int member = cur[1];
            List<MemberInfo> memberList = new ArrayList<>();
            if (TEAM_MAP.containsKey(leader)) {
                memberList = TEAM_MAP.get(leader);
            } else {
                MemberInfo memberInfo = new MemberInfo(leader, SALES[leader-1]);
                memberList.add(memberInfo);
            }
            MemberInfo memberInfo = new MemberInfo(member, SALES[member-1]);
            memberList.add(memberInfo);
            TEAM_MAP.put(leader, memberList);
        }
    }

    public static void combination(int[] items, int[] buckets, int k) {
        if (k == 0) {
            Set<Integer> attendSet = new HashSet<>();
            int index = 0;
            int sum = 0;
            for (int key : TEAM_MAP.keySet()) {
                List<MemberInfo> memberList = TEAM_MAP.get(key);
                MemberInfo member = memberList.get(buckets[index]);
                if (attendSet.add(member.memberNo)) {
                    sum += SALES[member.memberNo-1];
                    if (sum > MIN) {
                        return;
                    }
                }
                index++;
            }
            MIN = Math.min(MIN, sum);
            return;
        }

        int index = k - 1;
        for (int i=0; i <= items[index]-1; i++) {
            buckets[index] = i;
            combination(items, buckets, k - 1);
        }
    }
}
