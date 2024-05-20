from django.urls import path
from .views import UserRegistrationAPIView

urlpatterns = [
    path('signin/', UserRegistrationAPIView.as_view(), name='registro'),
    # Añade más rutas aquí
]