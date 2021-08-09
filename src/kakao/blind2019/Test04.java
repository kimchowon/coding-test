package kakao.blind2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 매칭 점수
 */
public class Test04 {
    public static void main(String[] args) {
/*        String word = "blind";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
       */

        String word = "Muzi";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};

        int answer = solution(word, pages);
        System.out.println(answer);
    }

    public static class PageInfo {
        private int index; // 인덱스
        private int basicScore; // 기본 점수
        private int externalLinkCnt; // 외부 링크수
        private List<Integer> connectedLinkList;
        private double linkScore; // 링크 점수
        private double matchingScore; // 매칭 점수


        public PageInfo(int index, int basicScore, int externalLinkCnt, List<Integer> connectedLinkList, double linkScore, double matchingScore) {
            this.index = index;
            this.basicScore = basicScore;
            this.externalLinkCnt = externalLinkCnt;
            this.connectedLinkList = connectedLinkList;
            this.linkScore = linkScore;
            this.matchingScore = matchingScore;
        }
    }

    public static Map<String, Integer> linkNameMap = new HashMap<>();

    public static int solution(String word, String[] pages) {
        int answer = 0;

        for (int i = 0; i < pages.length; i++) {
            setLinkName(pages[i], i);
        }

        // 기본 점수 및 외부 링크수 구하기
        List<PageInfo> pageInfoList = new ArrayList<>();
        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            int basicScore = getBasicScore(word, page);
            List<Integer> connectedLinkList = getConnectedLinkList(page);
            PageInfo pageInfo = new PageInfo(i, basicScore, connectedLinkList.size(), connectedLinkList, 0, 0);
            pageInfoList.add(pageInfo);
        }

        // 링크점수 구하기
        for (int i = 0; i < pageInfoList.size(); i++) {
            PageInfo pageInfo = pageInfoList.get(i);
            List<Integer> connectedLinkList = pageInfo.connectedLinkList;

            for (int link : connectedLinkList) {
                if (link > -1) {
                    double linkScore = pageInfo.basicScore / (double) pageInfo.externalLinkCnt;
                    pageInfoList.get(link).linkScore += linkScore;
                }
            }
        }

        // 매칭점수 구하기
        for (int i = 0; i < pageInfoList.size(); i++) {
            PageInfo pageInfo = pageInfoList.get(i);

            double matchingScore = pageInfo.basicScore + pageInfo.linkScore;
            pageInfo.matchingScore = matchingScore;
            pageInfoList.set(i, pageInfo);
        }

        pageInfoList.sort((o1, o2) -> {
            int result = Double.compare(o1.matchingScore, o2.matchingScore);
            if (result == 0) {
                return Integer.compare(o1.index, o2.index);
            }
            return -result;
        });

        return pageInfoList.get(0).index;
    }

    public static void setLinkName(String page, int index) {
        String linkName;
        String[] textList = page.split("<head>");
        textList = textList[1].split("</head>");
        String head = textList[0];

        int cnt = head.indexOf("content=\"");
        head = head.substring(cnt).replaceAll("content=\"", "");
        cnt = head.indexOf("\"/>");
        linkName = head.substring(0, cnt);
        linkNameMap.put(linkName, index);
    }

    // 기본 점수 얻기
    public static int getBasicScore(String word, String page) {
        int basicScore = 0;
        String text = getText(page.toLowerCase());
        text = text.replaceAll("[^a-zA-Z]", " ");

        String[] wordList = text.split(" ");
        word = word.toLowerCase();
        for (String curWord : wordList) {
            if (curWord.equals(word)) {
                basicScore++;
            }
        }
        return basicScore;
    }

    public static String getBody(String page) {
        String body;
        String[] textList = page.split("<body>");
        textList = textList[1].split("</body>");
        body = textList[0];
        return body;
    }

    public static String getText(String page) {
        String body = getBody(page);
        String text = body.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return text;
    }

    public static List<Integer> getConnectedLinkList(String page) {
        List<Integer> connectedLinkList = new ArrayList<>();
        String body = getBody(page);

        int cnt = 0;
        while (cnt != -1) {
            cnt = body.indexOf("<a href=\"");
            if (cnt >= 0) {
                body = body.substring(cnt + "<a href=\"".length());
                int lastCnt = body.indexOf("\">");
                String href = body.substring(0, lastCnt);
                linkNameMap.putIfAbsent(href, -1);
                connectedLinkList.add(linkNameMap.get(href));
                body = body.substring(lastCnt + "\">".length());
            }
        }
        return connectedLinkList;
    }
}
