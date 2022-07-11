package android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.passage;

import java.util.List;

public class TopPassageBean {

    /**
     * data
     */
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        public String printlnDate(){
            StringBuilder stringBuilder  = new StringBuilder();
            stringBuilder.append("author:"+author+"\n");
            stringBuilder.append("chapterId:"+chapterId+"\n");
            stringBuilder.append("chapterName:"+chapterName+"\n");
            stringBuilder.append("link:"+link+"\n");
            stringBuilder.append("niceDate:"+niceDate+"\n");
            stringBuilder.append("niceShareDate:"+niceShareDate+"\n");
            stringBuilder.append("shareUser:"+shareUser+"\n");
            stringBuilder.append("title:"+title+"\n");
            stringBuilder.append("superChapterName:"+superChapterName+"\n");
            return stringBuilder.toString();
        }
        /**
         * author
         */
        private String author;
        /**
         * chapterId
         */
        private Integer chapterId;
        /**
         * chapterName
         */
        private String chapterName;
        /**
         * link
         */
        private String link;
        /**
         * niceDate
         */
        private String niceDate;
        /**
         * niceShareDate
         */
        private String niceShareDate;
        /**
         * shareUser
         */
        private String shareUser;
        /**
         * superChapterName
         */
        private String superChapterName;
        /**
         * title
         */
        private String title;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Integer getChapterId() {
            return chapterId;
        }

        public void setChapterId(Integer chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

























