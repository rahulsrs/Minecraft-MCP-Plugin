import io.github.rahulsrs.aichat.AiChat;
import org.bukkit.Server;
import org.bukkit.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;


public class PluginEnable {


    World world;
    //Init Server
    ServerMock server = MockBukkit.mock();
    @BeforeEach
    public void setUp() {
        // Init Plugin before test
        AiChat plugin = MockBukkit.load(AiChat.class);
        this.world = server.addSimpleWorld("test");
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void thisTestWillFail() {

    }

    @Test
    public void playerSendsMessage(){
        PlayerMock player = server.addPlayer();
        player.sendMessage("Hi");
    }

}
