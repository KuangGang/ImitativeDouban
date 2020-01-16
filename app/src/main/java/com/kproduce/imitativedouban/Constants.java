package com.kproduce.imitativedouban;

import com.kproduce.imitativedouban.bean.Actor;
import com.kproduce.imitativedouban.bean.Comment;
import com.kproduce.imitativedouban.bean.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kuanggang on 2020/01/05
 */
public class Constants {
    public static List<Movie> DATAS;
    public static List<Actor> ACTORS;
    public static List<Comment> COMMENTS;

    static {
        DATAS = new ArrayList<>();
        ACTORS = new ArrayList<>();
        COMMENTS = new ArrayList<>();

        DATAS.add(new Movie("七宗罪",
                "Se7en(1995)",
                "“暴食”、“贪婪”、“懒惰”、“嫉妒”、“骄傲”、“淫欲”、“愤怒”，这是天主教教义所指的人性七宗罪。城市中发生的连坏杀人案，死者恰好都是犯有这些教义的人。凶手故弄玄虚的作案手法，令资深冷静的警员沙摩塞（摩根•弗里曼 Morgan Freeman 饰）和血气方刚的新扎警员米尔斯（布拉德•皮特 Brad Pitt 饰）都陷入了破案的谜团中。他们去图书馆研读但丁的《神曲》，企图从人间地狱的描绘中找到线索，最后从宗教文学哲学的世界中找到了凶手作案计划和手段的蛛丝马迹。凶手前来投案自首，这令众人都松了一口气，以为案件就此结束，怎料还是逃不出七宗罪的杀人逻辑，这次凶手瞄准的目标，是那个犯了“愤怒”罪的人……",
                R.mipmap.ic_cover_seven,
                "美国 / 剧情 犯罪 悬疑 / 上映时间：1995-09-22(美国) / 片长：127分钟"));
        DATAS.add(new Movie("小丑",
                "Joker(2019)",
                "电影《小丑》以同名DC漫画角色为基础，由华纳兄弟影业公司发行，计划于2019年10月4日上映。本片的故事将独立于DCEU之外，故事背景设置在20世纪80年代，讲述了一位生活陷入困境的脱口秀喜剧演员渐渐走向精神的崩溃，在哥谭市开始了疯狂的犯罪生涯，最终成为了蝙蝠侠的宿敌“小丑”的故事。本片由《宿醉》的导演托德菲利普斯执导，他与编剧斯科特西尔弗一起撰写了编剧。杰昆菲尼克斯本片中饰演主人公“小丑”，其他的主演包括罗伯特德尼罗、莎姬贝兹、马克马龙等。",
                R.mipmap.ic_cover_joker,
                "美国 / 剧情 犯罪 惊悚 / 上映时间：2019-08-31(威尼斯电影节) / 片长：122分钟"));
        DATAS.add(new Movie("致命ID",
                "Identity(2003)",
                "一个典型而又引人入胜的悬疑故事：一个汽车旅馆里，住进了10个人，他们中间有司机、妓女、过气女星、夫妇、警探和他的犯人，还有神秘的旅馆经理。这天风雨大作，通讯中断，10人被困在了旅馆里，惊悚的故事开始了。他们一个接一个的死去，并且按照顺序留下牌号。10个人存活下来的渐渐变少，他们开始恐慌，互相猜忌，却无意间发现了彼此间的联系。但是，大家怀疑的嫌疑人却纷纷死去，谜团笼罩在旅馆狭小的空间里，这样的凶杀案件却有着人们猜不到的真相……",
                R.mipmap.ic_cover_identity,
                "美国 / 剧情 悬疑 惊悚 / 上映时间：2003-04-25(美国) / 片长：90分钟"));

        ACTORS.add(new Actor("大卫·芬奇", "导演", R.mipmap.ic_actor_01));
        ACTORS.add(new Actor("摩根·弗里曼", "演员", R.mipmap.ic_actor_02));
        ACTORS.add(new Actor("布拉德·皮特", "演员", R.mipmap.ic_actor_03));
        ACTORS.add(new Actor("凯文·史派西", "演员", R.mipmap.ic_actor_04));
        ACTORS.add(new Actor("格温妮斯·帕特洛", "演员", R.mipmap.ic_actor_05));
        ACTORS.add(new Actor("安德鲁·凯文·沃克", "演员", R.mipmap.ic_actor_06));

        COMMENTS.add(new Comment("安西",
                "2005年12月26日",
                "捧着茶看的，挺好，呵呵。",
                R.mipmap.ic_avatar_01));
        COMMENTS.add(new Comment("天才樱木",
                "2011年8月13日",
                "场面还挺刺激的，但是完全看不懂啊喂！！有没有简单点搞笑的！！",
                R.mipmap.ic_avatar_02));
        COMMENTS.add(new Comment("三井",
                "2008年4月9日",
                "导演！我想拍电影！！",
                R.mipmap.ic_avatar_03));
        COMMENTS.add(new Comment("流川枫",
                "2006年2月11日",
                "看了半个小时就睡着了，醒来正好看到结局，挺好的。睡了。",
                R.mipmap.ic_avatar_04));
        COMMENTS.add(new Comment("宫本",
                "2008年9月15日",
                "女朋友推荐我看，没看进去，出去打球了！",
                R.mipmap.ic_avatar_05));
        COMMENTS.add(new Comment("赤木刚宪",
                "2007年10月2日",
                "多年以后再看，还是功力和锐气并重的嘴贫",
                R.mipmap.ic_avatar_06));
    }
}
