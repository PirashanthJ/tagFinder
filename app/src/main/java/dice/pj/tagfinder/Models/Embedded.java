package dice.pj.tagfinder.Models;

import com.google.gson.annotations.SerializedName;

public class Embedded
{
        @SerializedName("tags")  private Tag[] tag;

        public Tag[] getTags()
        {
            return tag;
        }

}
