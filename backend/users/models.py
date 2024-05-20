from django.db import models
from django.contrib.auth.models import AbstractUser

class CustomUser(AbstractUser):
    telefono = models.CharField(max_length=15, blank=True)

    def __str__(self):
        return self.username


class Address(models.Model):
    user = models.ForeignKey(CustomUser, on_delete=models.CASCADE)
    street = models.CharField(max_length=255)
    city = models.CharField(max_length=255)
    zipCode = models.CharField(max_length=10)
    country = models.CharField(max_length=255)
    phone = models.CharField(max_length=15, blank=True)

    def __str__(self):
        return f"{self.user.username} - {self.direccion} - {self.ciudad} - {self.pais}"
