package ch.hslu.ad.sw04.ex04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CharWrapper {

	private static final Logger logger = LogManager.getLogger("CharWrapper");

	private final Character character;

	public CharWrapper(Character character) {
		this.character = character;
	}

	public Character getCharacter() {
		return character;
	}

	@Override
	public boolean equals(Object other) {
		boolean equality = false;
		if (other == null) {
			equality = false;
		} else if (this == other) {
			equality = true;
		} else if (!(other instanceof CharWrapper)) {
			return false;
		} else {
			CharWrapper otherCharWrapper = (CharWrapper) other;
			equality = character.equals(otherCharWrapper.character);
		}
		logger.debug(String.format("'%s'.equals('%s')? %s", this.toString(), other.toString(), equality));
		return equality;
	}

	@Override
	public int hashCode() {
		int hashCode = character.hashCode();
		logger.debug(String.format("'%s'.hashCode() == %d", this.toString(), hashCode));
		return hashCode;
	}

	@Override
	public String toString() {
		return String.valueOf(character);
	}
}
