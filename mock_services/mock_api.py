import json
import socket

# Simulação dos dados
produtos = {
    "1b2da7cc-b367-4196-8a78-9cfeec21f587": {
        "id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
        "name": "Seguro de Vida",
        "created_at": "2021-07-01T00:00:00Z",
        "active": True,
        "offers": [
            "adc56d77-348c-4bf0-908f-22d402ee715c",
            "bdc56d77-348c-4bf0-908f-22d402ee715c",
            "cdc56d77-348c-4bf0-908f-22d402ee715c"
        ]
    }
}

ofertas = {
    "adc56d77-348c-4bf0-908f-22d402ee715c": {
        "id": "adc56d77-348c-4bf0-908f-22d402ee715c",
        "product_id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
        "name": "Seguro de Vida Familiar",
        "created_at": "2021-07-01T00:00:00Z",
        "active": True,
        "coverages": {
            "Incêndio": 500000.00,
            "Desastres naturais": 600000.00,
            "Responsabilidade civil": 80000.00,
            "Roubo": 100000.00
        },
        "assistances": [
            "Encanador",
            "Eletricista",
            "Chaveiro 24h",
            "Assistência Funerária"
        ],
        "monthly_premium_amount": {
            "max_amount": 100.74,
            "min_amount": 50.00,
            "suggested_amount": 60.25
        }
    }
}

def handle_request(data):
    request_line = data.split("\n")[0]
    method, path, _ = request_line.split(" ")

    if method == "GET" and path.startswith("/servicos-externos/consulta-produto/"):
        produto_id = path.split("/")[-1]
        produto = produtos.get(produto_id, {"error": "Produto não encontrado"})
        return json.dumps(produto)

    if method == "GET" and path.startswith("/servicos-externos/consulta-oferta/"):
        oferta_id = path.split("/")[-1]
        oferta = ofertas.get(oferta_id, {"error": "Oferta não encontrada"})
        return json.dumps(oferta)

    return json.dumps({"error": "Rota não encontrada"}), 404


def run_server(host="0.0.0.0", port=9091):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.bind((host, port))
        server_socket.listen(5)
        print(f"Servidor rodando em {host}:{port}...")

        while True:
            client_socket, _ = server_socket.accept()
            with client_socket:
                request_data = client_socket.recv(1024).decode()
                if not request_data:
                    continue
                response_body = handle_request(request_data)
                response = f"HTTP/1.1 200 OK\nContent-Type: application/json\n\n{response_body}"
                client_socket.sendall(response.encode())


if __name__ == "__main__":
    run_server()