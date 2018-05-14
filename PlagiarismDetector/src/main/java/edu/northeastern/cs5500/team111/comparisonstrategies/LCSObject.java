package edu.northeastern.cs5500.team111.comparisonstrategies;

/**
 * Created by lixin on 4/6/18.
 */
public class LCSObject {
    private String content;
    private int start;
    private int end;

    /**
     * Class constructor, initializing parameters
     */
    public LCSObject(String content, int start, int end) {
        this.content = content;
        this.start = start;
        this.end = end;
    }

    /**
     * Getters & setters
     */
    public String getContent() {
        return content;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    /** overriding equals to define what constitutes
     * two LCSObject to be equal
     * @param o second object to compare with this one
     * @return true/false based on two object are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof LCSObject)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        LCSObject c = (LCSObject) o;

        // Compare the data members and return accordingly
        return content.equals(c.content);
    }

    /**
      We let Super class handle the hashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
