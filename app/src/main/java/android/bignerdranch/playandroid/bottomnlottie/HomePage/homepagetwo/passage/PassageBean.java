package android.bignerdranch.playandroid.bottomnlottie.HomePage.homepagetwo.passage;

import java.util.List;

public class PassageBean {


    /**
     * data
     */
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * datas
         */
        private List<DatasBean> datas;

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
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
}
