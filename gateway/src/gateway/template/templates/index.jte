@import gateway.database.Message
@import java.util.List
@param List<Message> messages


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
    </style>
</head>
<body>
<h2>Messages</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Phone</th>
        <th>Content</th>
    </tr>
    @for (var message : messages)
        <tr>
            <td>${message.id}</td>
            <td>${message.phone}</td>
            <td>${message.content}</td>
        </tr>
    @endfor
</table>
</body>
</html>
