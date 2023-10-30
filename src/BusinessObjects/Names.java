package BusinessObjects;

import java.util.Random;

public class Names {
    private final String[] names = {
            "Emma Johnson", "Liam Smith", "Olivia Brown", "Noah Taylor", "Ava Martinez", "Isabella Williams", "Sophia Jones", "Mia Anderson", "Charlotte Garcia", "Amelia Davis",
            "Harper Rodriguez", "Evelyn Miller", "Abigail Wilson", "Emily Moore", "Elizabeth Taylor", "Mila Brown", "Ella Lewis", "Avery Hall", "Sofia Clark", "Camila Young",
            "Luna Scott", "Scarlett Adams", "Penelope Walker", "Layla King", "Chloe Wright", "Victoria Allen", "Madison Hill", "Eleanor Baker", "Grace Carter", "Nora Gonzalez",
            "Riley Carter", "Zoey Perez", "Hannah Hughes", "Hazel Carter", "Lily Reed", "Ellie Nelson", "Violet Coleman", "Aurora Ward", "Aria Cooper", "Cora Rivera",
            "Lucy Brooks", "Nova Alexander", "Liliana Torres", "Kinsley Murphy", "Laila James", "Blake Foster", "Ariana Long", "Anna Peterson", "Leah Howard", "Savannah Reed",
            "Zoe White", "Natalie Green", "Stella Bennett", "Aubrey Foster", "Faith Sanders", "Alexa Simmons", "Madeline Hayes", "Katherine Cooper", "Elena Parker", "Lila Gray",
            "Gabriella Hall", "Liam Turner", "Noah Morris", "Oliver Butler", "Elijah Hughes", "William Foster", "James Powell", "Benjamin Murphy", "Lucas Griffin", "Henry Collins",
            "Alexander Perry", "Sebastian Cox", "Jack Wood", "Aiden Brooks", "Owen Bennett", "Caleb Price", "Mateo Ward", "David Bailey", "Joseph Powell", "Samuel Griffin",
            "Oscar Scott", "Matthew Wood", "Michael Turner", "Jackson Murphy", "Mason Hayes", "Logan Carter", "Luke Reed", "Jayden Perry", "Daniel Gray", "Ezra Butler",
            "Theodore Foster", "Landon Simmons", "Christopher Long", "Anthony Cooper", "Jason Brooks", "Andrew Green", "Cameron Price", "William Baker", "David Price", "Elijah Cox",
            "James Cox", "John Hayes", "Luke Foster", "Matthew Simmons", "Samuel Powell", "Michael Turner", "Benjamin Gray", "Joseph Collins", "Daniel Wood", "Nicholas Long",
            "Caleb Wood", "Gabriel Griffin", "William Bennett", "Dylan Morris", "Jackson Butler", "Christopher Griffin", "Henry Long", "Nathaniel Bennett", "Mason Wood", "Isaac Foster",
            "Jayden Murphy", "Jonathan Simmons", "Liam Turner", "Alexander Green", "Ryan Collins", "Jack Turner", "David Wood", "Thomas Collins", "Carter Perry", "Matthew Foster",
            "Samuel Murphy", "Nicholas Gray", "Ethan Griffin", "Andrew Bailey", "William Long", "David Simmons", "Joseph Simmons", "Gabriel Perry", "Benjamin Green", "Caleb Baker",
            "Jackson Perry", "Christopher Morris", "Henry Hayes", "Nathaniel Foster", "Mason Simmons", "Isaac Carter", "Jayden Gray", "Jonathan Reed", "Liam Foster", "Alexander Hayes",
            "Ryan Collins", "Jack Simmons", "David Long", "Thomas Foster", "Carter Griffin", "Matthew Simmons", "Samuel Turner", "Nicholas Collins", "Ethan Perry", "Andrew Cox",
            "William Hayes", "Christopher Cox", "Landon Griffin", "Jonathan Perry", "Jackson Price", "David Price", "Anthony Collins", "James Cox", "Joseph Hayes", "Gabriel Butler",
            "Benjamin Price", "Caleb Wood", "Luke Foster", "Daniel Wood", "Lucas Simmons", "Nathaniel Long", "John Gray", "Cameron Wood", "Elijah Cox", "Isaiah Gray",
            "Thomas Turner", "Jack Foster", "Logan Gray", "Christian Bennett", "Jonah Perry", "Colton Simmons", "William Foster", "Julian Hayes", "Ryan Simmons", "William Wood",
            "Alexander Simmons", "James Long", "Daniel Simmons", "Liam Butler", "Matthew Perry", "Jackson Price", "Michael Price", "Henry Price", "Andrew Collins", "Benjamin Long",
            "David Powell", "Joseph Foster", "Caleb Cox", "Gabriel Price", "Jonathan Wood", "Samuel Foster", "Logan Simmons", "Luke Perry", "Thomas Bennett", "Ethan Foster",
            "Cameron Wood", "David Bailey", "Nathaniel Price", "Ryan Baker", "Christian Wood", "Landon Long", "Jonah Foster", "Christopher Perry", "Nicholas Griffin", "James Simmons",
            "Luke Long", "Lucas Perry", "Nathaniel Turner", "John Cox", "Jack Gray", "Elijah Simmons", "Ethan Perry", "Caleb Baker", "Michael Collins", "Jonathan Perry", "Joseph Hayes",
            "Colton Simmons", "Isaiah Gray", "Thomas Turner", "Jack Foster", "Christian Bennett", "Logan Gray", "Julian Hayes", "Ryan Simmons", "William Wood", "James Long",
            "Alexander Simmons", "Gabriel Price", "Liam Butler", "Matthew Perry", "Nathaniel Price", "Michael Price", "Daniel Simmons", "Andrew Collins", "Caleb Cox", "Jonathan Wood",
            "Benjamin Long", "David Powell", "Lucas Simmons", "Jack Perry", "Cameron Wood", "Ethan Foster", "Thomas Turner", "Landon Griffin", "Logan Simmons", "Elijah Cox",
            "Colton Price", "William Foster", "Isaiah Gray", "Christian Wood", "Nathaniel Bennett", "Jonah Perry", "James Cox", "Luke Foster", "Matthew Simmons", "Jackson Perry",
            "Henry Hayes", "Anthony Collins", "Gabriel Butler", "Jonathan Perry", "Benjamin Price", "David Price", "Daniel Wood", "Joseph Simmons", "William Long", "Caleb Baker",
            "Luke Foster", "Samuel Turner", "David Long", "Nicholas Collins", "Jonathan Simmons", "Ethan Perry", "William Hayes", "Jack Simmons", "Christopher Cox", "Thomas Collins",
            "Andrew Bailey", "Carter Perry", "Elijah Simmons", "Mason Wood", "Lucas Hayes", "Colton Simmons", "Liam Foster", "Gavin Baker", "Nolan Cox", "Eli Bennett", "Skyler Hayes"
    };

    public String getRandomName(){
        Random rnd = new Random();
        int index = rnd.nextInt(names.length);
        //ArrayUtils.removeElement(names, index)
        return names[index];
    }


}
