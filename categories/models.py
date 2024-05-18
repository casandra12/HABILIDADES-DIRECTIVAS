from django.db import models

# Modelo de Categoría para organizar productos
class Category(models.Model):
    name = models.CharField(max_length=255, unique=True)  # Nombre de la categoría

    def __str__(self):
        return self.name  # Devuelve el nombre de la categoría como representación legible del objeto
