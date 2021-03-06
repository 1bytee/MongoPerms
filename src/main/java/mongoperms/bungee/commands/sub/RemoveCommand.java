package mongoperms.bungee.commands.sub;

import mongoperms.Messages;
import mongoperms.MongoConnection;
import mongoperms.bungee.commands.SubCommand;
import net.md_5.bungee.api.CommandSender;

public class RemoveCommand extends SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        String group = args[0];
        String permission = args[1];
        MongoConnection.Result result = MongoConnection.removePermission(group, permission);

        switch (result) {
            case SUCCESS:
                sender.sendMessage(Messages.PERMISSION_REMOVED.toComponent(permission, group));
                break;
            case UNKNOWN_GROUP:
                sender.sendMessage(Messages.UNKNOWN_GROUP.toComponent(group));
                break;
            case UNKNOWN_PERMISSION:
                sender.sendMessage(Messages.UNKNOWN_PERMISSION.toComponent(group, permission));
                break;
        }
    }

    @Override
    public int requiredArgs() {
        return 2;
    }

    @Override
    public String getUsage() {
        return "/perms remove <Group> <Permission>";
    }

}
