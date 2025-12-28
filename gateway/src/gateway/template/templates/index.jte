@import gateway.database.Message
@import java.util.List
@import java.time.format.DateTimeFormatter
@param List<Message> messages


@var DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Messages</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:hover { background-color: #f9f9f9; }
    </style>
</head>
<body>
<h2>Messages</h2>

@if(messages.isEmpty())
    <p>No messages found.</p>
@else
    <table>
        <tr>
            <th>ID</th>
            <th>Phone</th>
            <th>Content</th>
            <th>Status</th>
            <th>Driver</th>
            <th>Created At</th>
        </tr>
        @for(var message : messages)
            <tr>
                <td>${message.getId()}</td>
                <td>${message.getReceiver()}</td>
                <td>${message.getContent()}</td>
                <td>${message.getStatus()}</td>
                <td>${message.getDriver()}</td>
                <td>${message.getCreatedAt().format(formatter)}</td>
            </tr>
        @endfor
    </table>
@endif
</body>
</html>
