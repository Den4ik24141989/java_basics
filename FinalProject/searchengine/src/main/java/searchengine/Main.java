package searchengine;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;
import searchengine.parsers.LemmaFinder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.*;

public class Main {

    private static final Random random = new Random();
    private static final String WORD_TYPE_REGEX = "\\W\\w&&[^а-яА-Я\\s]";

    private static final String[] particlesNames = new String[]{"МЕЖД", "ПРЕДЛ", "СОЮЗ"};
    public static void main(String[] args) throws Exception{
        LuceneMorphology luceneMorph =
                new RussianLuceneMorphology();
//        List<String> wordBaseForms =
//                luceneMorph.getNormalForms("вой");
//        wordBaseForms.forEach(System.out::println);
//        System.out.println(System.currentTimeMillis() - (random.nextInt(10_000)));
        LocalDateTime localDateTime = LocalDateTime.now();
//MICRO_OF_DAY MILLI_OF_SECOND  MILLI_OF_DAY MICRO_OF_SECOND
        long s = localDateTime.toEpochSecond(ZoneOffset.UTC);
        long a = localDateTime.getLong(ChronoField.NANO_OF_DAY) / 10;
        System.out.println(s);
        System.out.println(a);


//        String text = "Повторное появление леопарда в Осетии позволяет предположить, что леопард постоянно обитает в некоторых районах Северного Кавказа";
//        LemmaFinder lemmaFinder = LemmaFinder.getInstance();
//        System.out.println("getLemmaSet :" + lemmaFinder.getLemmaSet(text));
//        System.out.println("collectLemmas :" + lemmaFinder.collectLemmas(text));

//        List <String> words = Arrays.stream(arrayContainsRussianWords(text)).toList();
//        for (String word : words) {
//            if (hasParticleProperty(word)) {
//                List<String> wordBaseForms =
//                        luceneMorph.getNormalForms(word);
//                System.out.println(wordBaseForms);
//            }
//        }
    }
    private static List <String> words (String text) {
        String regex = "[^а-яА-ЯёЁ\\s]";
        text = text.replaceAll(WORD_TYPE_REGEX, "").toLowerCase();
        List<String> words = new ArrayList<>();
        String[] splitText = text.split("\\W\\s+");
        words.addAll(Arrays.asList(splitText));
        return words;
    }
    private static String[] arrayContainsRussianWords(String text) {
        return text.toLowerCase(Locale.ROOT)
                .replaceAll("([^а-я\\s])", " ")
                .trim()
                .split("\\s+");
    }

    private static boolean hasParticleProperty(String wordBase) {
        for (String property : particlesNames) {
            if (wordBase.toUpperCase().contains(property)) {
                return true;
            }
        }
        return false;
    }
}
