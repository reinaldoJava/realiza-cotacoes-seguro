import json
from http.server import BaseHTTPRequestHandler, HTTPServer

# Dados simulados
produtos = {
    "1b2da7cc-b367-4196-8a78-9cfeec21f587": {
        "id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
        "name": "Seguro de Vida",
        "created_at": "2021-07-01T00:00:00Z",
        "active": True
    }
}

class SimpleHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path.startswith("/servicos-externos/consulta-produto/"):
            produto_id = self.path.split("/")[-1]
            produto = produtos.get(produto_id, {"error": "Produto não encontrado"})

            self.send_response(200 if "error" not in produto else 404)
            self.send_header("Content-type", "application/json")
            self.end_headers()
            self.wfile.write(json.dumps(produto).encode())

        else:
            self.send_response(404)
            self.end_headers()
            self.wfile.write(b'{"error": "Rota nao encontrada"}')

# Inicializa o servidor na porta 9091
if __name__ == "__main__":
    server_address = ("0.0.0.0", 9091)
    httpd = HTTPServer(server_address, SimpleHandler)
    print("Servidor rodando na porta 9091...")
    httpd.serve_forever()