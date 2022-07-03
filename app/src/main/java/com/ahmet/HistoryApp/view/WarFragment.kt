package com.ahmet.HistoryApp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.adaptor.War_Adaptor
import com.ahmet.HistoryApp.model.War
import com.ahmet.HistoryApp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_war.*

class WarFragment : Fragment() {
    private val models=ArrayList<War>()
    private lateinit var adaptery:War_Adaptor
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_war, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recycler_war.layoutManager=LinearLayoutManager(requireContext())
        adaptery= War_Adaptor(models)
        recycler_war.adapter=adaptery

        val napolyon=War(R.drawable.napolyonn,"Napolyon İhtilali","1751-1763","Fransa, Paris","Cengiz Han[not 1] (doğum adıyla Temuçin,[not 2] y. 1162 – 18 Ağustos 1227), Moğol İmparatorluğu'nun kurucusu ve ilk Kağanı olan Moğol komutan ve hükümdardır.[3] Hükümdarlığı döneminde gerçekleştirdiği hiçbir savaşı kaybetmeyen Cengiz Han, Dünya tarihinin en büyük askeri liderlerinden birisi olarak kabul edilmektedir. 13. yüzyılın başında Orta Asya'daki tüm göçebe bozkır kavimlerini birleştirip bir ulus hâline getirerek Moğol siyasi kimliği çatısı altında toplamış ve dünyanın en büyük bitişik sınırlara sahip imparatorluğunu kurmuştur.\n" +
                "\n" +
                "1162 civarında Moğolistan'daki Onon Nehri yakınlarında doğduğu düşünülen Cengiz Han'ın gerçek adı ''Temuçin''dir. Babası Yesügey, düşman bir kabile olan Tatarlar tarafından zehirlenerek öldürüldüğünde Temuçin henüz 9 yaşındaydı.[4] Temuçin'in kabilesi, küçük yaştaki bir çocuğun liderliğini kabul etmedi ve kardeşleri ve annesiyle birlikte onları ölüme terk ederek kabileden sürdüler. Moğolistan'ın acımasız bozkırlarında kaçarak ve saklanarak hayatta kalmaya çalıştılar. Temuçin daha küçücük bir çocukken, avladıkları bir hayvanı paylaşmak istemeyen üvey kardeşini çıkan bir kavga sonucu öldürdü. 16 yaşına geldiğinde, daha önceden yaptıkları bir anlaşma sayesinde nüfuzlu bir kabileden olan Börte isminde birisiyle evlendi. Henüz 20 yaşına geldiğinde, tüm Moğolistan'da tanınan ve saygı duyulan bir komutan haline gelmeyi başarmıştı. Moğolistan'daki göçebe kavimleri birer birer kendi bayrağı altında birleştirdi. 1206 yılına gelindiğinde Moğolistan'da birlik sağlanmıştı.")
        models.add(napolyon)
        val cengizhan=War(R.drawable.cengizhansavas,"Moğolistan Çin Savaşı","1201-1210","Çin, Shangai","Cengiz Han[not 1] (doğum adıyla Temuçin,[not 2] y. 1162 – 18 Ağustos 1227), Moğol İmparatorluğu'nun kurucusu ve ilk Kağanı olan Moğol komutan ve hükümdardır.[3] Hükümdarlığı döneminde gerçekleştirdiği hiçbir savaşı kaybetmeyen Cengiz Han, Dünya tarihinin en büyük askeri liderlerinden birisi olarak kabul edilmektedir. 13. yüzyılın başında Orta Asya'daki tüm göçebe bozkır kavimlerini birleştirip bir ulus hâline getirerek Moğol siyasi kimliği çatısı altında toplamış ve dünyanın en büyük bitişik sınırlara sahip imparatorluğunu kurmuştur.\n" +
                "\n" +
                "1162 civarında Moğolistan'daki Onon Nehri yakınlarında doğduğu düşünülen Cengiz Han'ın gerçek adı ''Temuçin''dir. Babası Yesügey, düşman bir kabile olan Tatarlar tarafından zehirlenerek öldürüldüğünde Temuçin henüz 9 yaşındaydı.[4] Temuçin'in kabilesi, küçük yaştaki bir çocuğun liderliğini kabul etmedi ve kardeşleri ve annesiyle birlikte onları ölüme terk ederek kabileden sürdüler. Moğolistan'ın acımasız bozkırlarında kaçarak ve saklanarak hayatta kalmaya çalıştılar. Temuçin daha küçücük bir çocukken, avladıkları bir hayvanı paylaşmak istemeyen üvey kardeşini çıkan bir kavga sonucu öldürdü. 16 yaşına geldiğinde, daha önceden yaptıkları bir anlaşma sayesinde nüfuzlu bir kabileden olan Börte isminde birisiyle evlendi. Henüz 20 yaşına geldiğinde, tüm Moğolistan'da tanınan ve saygı duyulan bir komutan haline gelmeyi başarmıştı. Moğolistan'daki göçebe kavimleri birer birer kendi bayrağı altında birleştirdi. 1206 yılına gelindiğinde Moğolistan'da birlik sağlanmıştı.")
        models.add(cengizhan)
        val fsm=War(R.drawable.istanbul,"İStanbulun Fethi","1451-1453","Türkiye, İstanbul","Cengiz Han[not 1] (doğum adıyla Temuçin,[not 2] y. 1162 – 18 Ağustos 1227), Moğol İmparatorluğu'nun kurucusu ve ilk Kağanı olan Moğol komutan ve hükümdardır.[3] Hükümdarlığı döneminde gerçekleştirdiği hiçbir savaşı kaybetmeyen Cengiz Han, Dünya tarihinin en büyük askeri liderlerinden birisi olarak kabul edilmektedir. 13. yüzyılın başında Orta Asya'daki tüm göçebe bozkır kavimlerini birleştirip bir ulus hâline getirerek Moğol siyasi kimliği çatısı altında toplamış ve dünyanın en büyük bitişik sınırlara sahip imparatorluğunu kurmuştur.\n" +
                "\n" +
                "1162 civarında Moğolistan'daki Onon Nehri yakınlarında doğduğu düşünülen Cengiz Han'ın gerçek adı ''Temuçin''dir. Babası Yesügey, düşman bir kabile olan Tatarlar tarafından zehirlenerek öldürüldüğünde Temuçin henüz 9 yaşındaydı.[4] Temuçin'in kabilesi, küçük yaştaki bir çocuğun liderliğini kabul etmedi ve kardeşleri ve annesiyle birlikte onları ölüme terk ederek kabileden sürdüler. Moğolistan'ın acımasız bozkırlarında kaçarak ve saklanarak hayatta kalmaya çalıştılar. Temuçin daha küçücük bir çocukken, avladıkları bir hayvanı paylaşmak istemeyen üvey kardeşini çıkan bir kavga sonucu öldürdü. 16 yaşına geldiğinde, daha önceden yaptıkları bir anlaşma sayesinde nüfuzlu bir kabileden olan Börte isminde birisiyle evlendi. Henüz 20 yaşına geldiğinde, tüm Moğolistan'da tanınan ve saygı duyulan bir komutan haline gelmeyi başarmıştı. Moğolistan'daki göçebe kavimleri birer birer kendi bayrağı altında birleştirdi. 1206 yılına gelindiğinde Moğolistan'da birlik sağlanmıştı.")
        models.add(fsm)
        val birinci=War(R.drawable.birincidunya,"1.Dünya Savaşı","1914-1918","Avrupa-Ortadoğu ve Asya","Cengiz Han[not 1] (doğum adıyla Temuçin,[not 2] y. 1162 – 18 Ağustos 1227), Moğol İmparatorluğu'nun kurucusu ve ilk Kağanı olan Moğol komutan ve hükümdardır.[3] Hükümdarlığı döneminde gerçekleştirdiği hiçbir savaşı kaybetmeyen Cengiz Han, Dünya tarihinin en büyük askeri liderlerinden birisi olarak kabul edilmektedir. 13. yüzyılın başında Orta Asya'daki tüm göçebe bozkır kavimlerini birleştirip bir ulus hâline getirerek Moğol siyasi kimliği çatısı altında toplamış ve dünyanın en büyük bitişik sınırlara sahip imparatorluğunu kurmuştur.\n" +
                "\n" +
                "1162 civarında Moğolistan'daki Onon Nehri yakınlarında doğduğu düşünülen Cengiz Han'ın gerçek adı ''Temuçin''dir. Babası Yesügey, düşman bir kabile olan Tatarlar tarafından zehirlenerek öldürüldüğünde Temuçin henüz 9 yaşındaydı.[4] Temuçin'in kabilesi, küçük yaştaki bir çocuğun liderliğini kabul etmedi ve kardeşleri ve annesiyle birlikte onları ölüme terk ederek kabileden sürdüler. Moğolistan'ın acımasız bozkırlarında kaçarak ve saklanarak hayatta kalmaya çalıştılar. Temuçin daha küçücük bir çocukken, avladıkları bir hayvanı paylaşmak istemeyen üvey kardeşini çıkan bir kavga sonucu öldürdü. 16 yaşına geldiğinde, daha önceden yaptıkları bir anlaşma sayesinde nüfuzlu bir kabileden olan Börte isminde birisiyle evlendi. Henüz 20 yaşına geldiğinde, tüm Moğolistan'da tanınan ve saygı duyulan bir komutan haline gelmeyi başarmıştı. Moğolistan'daki göçebe kavimleri birer birer kendi bayrağı altında birleştirdi. 1206 yılına gelindiğinde Moğolistan'da birlik sağlanmıştı.")
        models.add(birinci)
        val ikinci=War(R.drawable.ikincidunya,"2.Dünya Savaşı","1939-1945","Avrupa-Ortadoğu ve Asya","Cengiz Han[not 1] (doğum adıyla Temuçin,[not 2] y. 1162 – 18 Ağustos 1227), Moğol İmparatorluğu'nun kurucusu ve ilk Kağanı olan Moğol komutan ve hükümdardır.[3] Hükümdarlığı döneminde gerçekleştirdiği hiçbir savaşı kaybetmeyen Cengiz Han, Dünya tarihinin en büyük askeri liderlerinden birisi olarak kabul edilmektedir. 13. yüzyılın başında Orta Asya'daki tüm göçebe bozkır kavimlerini birleştirip bir ulus hâline getirerek Moğol siyasi kimliği çatısı altında toplamış ve dünyanın en büyük bitişik sınırlara sahip imparatorluğunu kurmuştur.\n" +
                "\n" +
                "1162 civarında Moğolistan'daki Onon Nehri yakınlarında doğduğu düşünülen Cengiz Han'ın gerçek adı ''Temuçin''dir. Babası Yesügey, düşman bir kabile olan Tatarlar tarafından zehirlenerek öldürüldüğünde Temuçin henüz 9 yaşındaydı.[4] Temuçin'in kabilesi, küçük yaştaki bir çocuğun liderliğini kabul etmedi ve kardeşleri ve annesiyle birlikte onları ölüme terk ederek kabileden sürdüler. Moğolistan'ın acımasız bozkırlarında kaçarak ve saklanarak hayatta kalmaya çalıştılar. Temuçin daha küçücük bir çocukken, avladıkları bir hayvanı paylaşmak istemeyen üvey kardeşini çıkan bir kavga sonucu öldürdü. 16 yaşına geldiğinde, daha önceden yaptıkları bir anlaşma sayesinde nüfuzlu bir kabileden olan Börte isminde birisiyle evlendi. Henüz 20 yaşına geldiğinde, tüm Moğolistan'da tanınan ve saygı duyulan bir komutan haline gelmeyi başarmıştı. Moğolistan'daki göçebe kavimleri birer birer kendi bayrağı altında birleştirdi. 1206 yılına gelindiğinde Moğolistan'da birlik sağlanmıştı.")
        models.add(ikinci)


    }



}