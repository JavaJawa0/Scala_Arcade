import java.io.{File, PrintWriter}
import scala.util.Random

@main def bootArcade(): Unit =
  println("Booting Scala Arcade Telemetry Engine...")
  
  val r = new Random()
  val games = List("Pac-Man", "Space Invaders", "Donkey Kong", "Galaga", "Frogger")
  val players = List("PixelPete", "ChompQueen", "KongKiller", "RetroRider", "BlipBlop", "SegaSam", "NeoGeo")
  
  val dir = new File("../data")
  if (!dir.exists()) dir.mkdir()
  
  val writer = new PrintWriter(new File("../data/telemetry.csv"))
  
  try {
    // Header row with all required columns
    writer.println("Player,Game,Score,Accuracy,PlayTimeMinutes,SkillTier")
    
    // Simulating 10,000 arcade sessions
    for (_ <- 1 to 10000) do
      val player = players(r.nextInt(players.length))
      val game = games(r.nextInt(games.length))
      val score = r.nextInt(150000) + 5000
      val accuracy = (r.nextDouble() * 40 + 60).round // 60% to 100%
      val playtime = r.nextInt(45) + 2 // 2 to 47 minutes
      
      // Pattern matching for skill tiers
      val skillTier = score match
        case s if s > 120000 => "Grandmaster"
        case s if s > 80000  => "Pro Gamer"
        case s if s > 30000  => "Casual"
        case _               => "Novice"
        
      writer.println(s"$player,$game,$score,$accuracy,$playtime,$skillTier")
      
    println("Success: Generated 10,000 arcade sessions in data/telemetry.csv")
  } finally {
    writer.close()
  }