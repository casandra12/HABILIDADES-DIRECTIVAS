from django.db import models  # Importa el módulo models de Django
from products.models import Product  # Importa el modelo Product de la otra aplicación

# Define el modelo Order para representar los pedidos
class Pedido(models.Model):
    # Campo que registra la fecha y hora en que se creó el pedido
    creacionFecha = models.DateTimeField(auto_now_add=True)
    # Campo que registra la fecha y hora de la última actualización del pedido
    actualizacionFecha= models.DateTimeField(auto_now=True)
    # Campo que almacena el nombre del cliente que realizó el pedido
    nombre = models.CharField(max_length=255)
    # Campo que almacena el correo electrónico del cliente que realizó el pedido
    correo= models.EmailField()

    # Método que devuelve una cadena con id del pedido y nombre del cliente
    def __str__(self):
        return f"Pedido {self.id} de {self.customer_name}"

# Define el modelo para representar los artículos dentro de un pedido
class PedidoItem(models.Model):
    # Indica que un artículo pertenece a un pedido
    orden = models.ForeignKey(Pedido, related_name='items', on_delete=models.CASCADE)
    # cada PedidoItem está asociado a un Orden específico
    cantidadProd = models.ForeignKey(Product, on_delete=models.CASCADE)
    # Campo que almacena la cantidad del producto en el pedido
    quantity = models.PositiveIntegerField()
    # Campo que almacena el precio del producto al momento del pedido
    price = models.FloatField()

#devuelve nombre del producto y cantidad 
    def __str__(self):
        return f"{self.quantity} of {self.product.name}"

    # Método que calcula el precio total del artículo en base a la cantidad y el precio unitario
    def get_total_price(self):
        return self.quantity * self.price

