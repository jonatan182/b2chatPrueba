docker build -t b2chat_orden_pago_image:1.0.0 .
docker run -p 8080:8080 --env SPRING_PROFILES_ACTIVE=docker --name b2chat_orden_pago b2chat_orden_pago_image:1.0.0

//Generar Orden De Compra
POST: http://127.0.0.1:8080/b2chat/generarOrdenCompra

request:
{
	"idCliente": 187,
	"numeroOrdenCompra": 183,
	"descripcion": "Compra de productos de Aseo",
	"fechaEntrega": "2022-07-02"
}

response:
{
    "status": true,
    "code": 200,
    "message": "Se agrego la orden de compra",
    "body": {
        "187": [
            {
                "idCliente": 187,
                "numeroOrdenCompra": 183,
                "descripcion": "Compra de productos de Aseo",
                "fechaEntrega": "2022-07-02"
            }
        ]
    }
}

//Consultar Orden De Compra
Get: http://127.0.0.1:8080/b2chat/consultaOrdenCompra/2022-07-02
