import java.util.Arrays;
import java.util.List;

import edu.smu.tspell.wordnet.*;
import edu.smu.tspell.wordnet.impl.file.PropertyNames;

public class WordNetTest {

	public WordNetTest() {
		database = WordNetDatabase.getFileInstance();
	}

	public List<Synset> getSynsets(String word) {
		return Arrays.asList(database.getSynsets(word));
	}

	public static void main(String[] args) {
		System.setProperty(PropertyNames.DATABASE_DIRECTORY, "/Users/markw/Documents/WORK/wordnet3/dict");
		System.out.println(System.getProperty(PropertyNames.DATABASE_DIRECTORY, "."));
		WordNetTest tester = new WordNetTest();
		String word = "bank";
		List<Synset> synset_list = tester.getSynsets(word);
		System.out.println("\n\n** Process word: " + word);
		for (Synset synset : synset_list) {
			System.out.println("\nsynset type:       " + SYNSET_TYPES[synset.getType().getCode()]);
			System.out.println("       definition: " + synset.getDefinition());
			// word forms are synonyms:
			for (String wordForm : synset.getWordForms()) {
				if (!wordForm.equals(word)) {
					System.out.println("       synonym:    " + wordForm);
					// antonyms mean the opposite:
					for (WordSense antonym : synset.getAntonyms(wordForm)) {
						//System.out.println(antonym);
						for (String opposite : antonym.getSynset().getWordForms()) {
							System.out.println("             antonym (of " + wordForm+"): " + opposite);
						}
					}
				}
			}
			System.out.println("\n");
		}
	}
	private WordNetDatabase database;
	private final static String[] SYNSET_TYPES = {"", "noun", "verb"};
}