package io.github.sadeqit.weatherino.data.model

private val iranianCityNames = mapOf(
    "Tehran" to "تهران",
    "Qom" to "قم",
    "Mashhad" to "مشهد",
    "Isfahan" to "اصفهان",
    "Shiraz" to "شیراز",
    "Tabriz" to "تبریز",
    "Karaj" to "کرج",
    "Ahvaz" to "اهواز",
    "Kermanshah" to "کرمانشاه",
    "Rasht" to "رشت",
    "Urmia" to "ارومیه",
    "Yazd" to "یزد",
    "Kerman" to "کرمان",
    "Ardabil" to "اردبیل",
    "Bandar Abbas" to "بندرعباس",
    "Zahedan" to "زاهدان",
    "Sanandaj" to "سنندج",
    "Hamadan" to "همدان",
    "Zanjan" to "زنجان",
    "Sari" to "ساری",
    "Gorgan" to "گرگان",
    "Bojnurd" to "بجنورد",
    "Birjand" to "بیرجند",
    "Bushehr" to "بوشهر",
    "Khorramabad" to "خرم‌آباد",
    "Ilam" to "ایلام",
    "Yasuj" to "یاسوج",
    "Shahr-e Kord" to "شهرکرد"
)

fun String.toIranianCityName(): String {
    return iranianCityNames[this] ?: this
}