POST http://localhost:8080/start
Body: {"file_path": "C:/10m.txt"}

Response: 202

GET http://localhost:8080/get_desc_sequence

{
    "max_descending_sequence": "[[47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762]]"
}

 GET http://localhost:8080/get_min_value?mediaType=xml

<Map>
    <minimum_value>-49999996</minimum_value>
</Map>

GET http://localhost:8080/operation
Body: {"operation": "get_max_value"}

{
    "maximum_value": "49999978"
}

GET http://localhost:8080/get_average_value
Header: Accept: application/xml

<Map>
    <average_value>7364</average_value>
</Map>
