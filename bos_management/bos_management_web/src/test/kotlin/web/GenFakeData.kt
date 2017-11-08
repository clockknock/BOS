package web

import com.itheima.bos.dao.base.CourierDao
import com.itheima.bos.dao.base.StandardDao
import com.itheima.bos.domain.base.Courier
import com.itheima.bos.domain.base.Standard
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*
import kotlin.coroutines.experimental.suspendCoroutine


/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:applicationContext.xml")
class GenFakeData {
    @Autowired private lateinit var courierDao: CourierDao
    @Autowired private lateinit var standardDao: StandardDao

    @Test
    fun genCourier() {
        for (x in 1..150) {
            courierDao.save(getCourier())
        }
    }

    private fun getCourier(): Courier {
        val courier = Courier()
        courier.checkPwd = "123"
        courier.company = rdmCompanyName()
        courier.courierNum = rdmPhone()
        courier.pda = rdmPhone()
        courier.vehicleNum = generateCarID()
        courier.type = rdmType()
        courier.standard = rdmStandard()
        courier.name = rdmName()
        courier.telephone = rdmMobile()
        courier.vehicleType =rdmVehicleType()
                return courier
    }

    private fun rdmStandard(): Standard {

        return standardDao.findOne(Random().nextInt(100) + 41)

    }


    private fun rdmCompanyName(): String {
        val rdm = Random()
        return names[rdm.nextInt(names.size)]
    }

    private fun rdmType(): String {
        val rdm = Random()
        return types[rdm.nextInt(types.size)]
    }

    private fun rdmVehicleType(): String {
        val rdm = Random()
        return vehicleType[rdm.nextInt(vehicleType.size)]
    }

    private fun rdmPhone(): String {
        val strs = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        val rdm = Random()
        val buffer = StringBuffer()
        for (i in 0..7) {
            buffer.append(strs[rdm.nextInt(strs.size)])
        }
        return buffer.toString()
    }

    private fun rdmMobile(): String {
        val strs = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        val rdm = Random()
        val buffer = StringBuffer("13")
        for (i in 0..10) {
            buffer.append(strs[rdm.nextInt(strs.size)])
        }
        return buffer.toString()
    }

    private fun generateCarID(): String {

        val provinceAbbr = charArrayOf(// 省份简称 4+22+5+3
                '京', '津', '沪', '渝', '冀', '豫', '云', '辽', '黑', '湘', '皖', '鲁', '苏', '浙', '赣', '鄂', '甘', '晋', '陕', '吉', '闽', '贵', '粤', '青', '川', '琼', '宁', '新', '藏', '桂', '蒙', '港', '澳', '台')
        val alphas = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890" // 26个字母 + 10个数字

        val random = Random() // 随机数生成器

        var carID = ""

        // 省份+地区代码+·  如 湘A· 这个点其实是个传感器，不过加上美观一些
        carID += provinceAbbr[random.nextInt(34)] // 注意：分开加，因为加的是2个char
        carID += alphas[random.nextInt(26)] + "·"

        // 5位数字/字母
        for (i in 0..4) {
            carID += alphas[random.nextInt(36)]
        }
        return carID
    }

    private var nameSex = ""
    private fun rdmName(): String {
        var index = getNum(0, firstName.length - 1)
        val first = firstName.substring(index, index + 1)
        val sex = getNum(0, 1)
        var str = boy
        var length = boy.length
        if (sex == 0) {
            str = girl
            length = girl.length
            nameSex = "女"
        } else {
            nameSex = "男"
        }
        index = getNum(0, length - 1)
        val second = str.substring(index, index + 1)
        val hasThird = getNum(0, 1)
        var third = ""
        if (hasThird == 1) {
            index = getNum(0, length - 1)
            third = str.substring(index, index + 1)
        }
        return first + second + third
    }

    private fun getNum(start: Int, end: Int): Int {
        return (Math.random() * (end - start + 1) + start).toInt()
    }


    private val types = arrayOf("小件员", "中件员", "大件员")
    private val vehicleType = arrayOf("迷你车", "小型车", "小型货车", "中型货车", "大型货车")
    private val names = arrayOf("华为技术有限公司", "中兴通讯股份有限公司", "海信集团有限公司", "UT斯达康通讯有限公司", "海尔集团公司", "神州数码(中国)有限公司", "浙江浙大网新科技股份有限公司", "熊猫电子集团有限公司", "浪潮集团有限公司", "东软集团有限公司", "北京北大方正集团", "微软(中国)有限公司", "朝华科技(集团)股份有限公司", "中国计算机软件与技术服务总公司", "清华同方股份有限公司", "上海贝尔阿尔卡特股份有限公司", "山东中创软件工程股份有限公司", "国际商业机器(中国)有限公司(IBM)", "大唐电信科技股份有限公司(北京)", "摩托罗拉(中国)电子有限公司", "上海宝信软件股份有限公司", "托普集团科技发展有限责任公司", "中国民航信息网络股份有限公司", "北京用友软件股份有限公司", "中国长城计算机集团公司", "北京四方继保自动化有限公司", "烟台东方电子信息产业集团有限公司", "北京甲骨文软件系统有限公司", "南京联创科技股份有限公司", "金蝶软件(中国)有限公司", "南京南瑞集团公司", "杭州恒生电子集团有限公司", "上海新华控制技术(集团)有限公司", "新太科技股份有限公司", "思爱普(北京)软件系统有限公司", "哈尔滨亿阳信通股份公司", "云南南天电子信息产业股份公司", "杭州新中大软件股份公司", "株洲时代集团公司", "南京南瑞继保电气有限公司", "江苏南大苏富特软件股份有限公司", "创智集团", "深圳市南凌科技发展有限公司", "北京握奇数据系统有限公司", "毕益辉系统(中国)有限公司(BEA)", "华立集团有限公司", "广州华南资讯科技有限公司", "杭州士兰微电子股份有限公司", "浙江中控科技集团有限公司", "盛趣信息技术(上海)有限公司", "长春一汽启明信息技术有限公司", "湖南计算机集团", "华北计算机系统工程研究所", "深圳迈瑞生物医疗电子股份有限公司", "福建星网锐捷通讯有限公司", "广州海格通信有限公司", "京华网络有限公司", "北京市和利时系统工程股份有限公司", "长江计算机(集团)公司(上海)", "太极计算机集团(北京)", "江苏东大金智软件股份有限公司", "杭州信雅达系统工程股份有限公司")

    private val firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续"
    private val girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 "
    private val boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘"

}