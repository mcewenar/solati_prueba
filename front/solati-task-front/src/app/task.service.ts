
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from './task.model';

//Servicio que comunica son el servidor de spring boot usando (inyección de dpendetencias)
//Un basic authenticarttion header userename/password

//El servicio tienr está provisto de un inyector root, lo cual se comparte por todo el proyecto, como un bean 
//(Patrón singleton) -> unica instancia en la app lo cual puede accederse desde cualquier lugar
@Injectable({ providedIn: 'root' })
export class TaskService {


  //URL BASE PARA CONSUMIR SERVIDOR DE SPRING BOOT
  //En entornos productivos, esto se obtiene de una environment
  private readonly apiUrl = 'http://localhost:8080/api/task';

  constructor(private http: HttpClient) {}

  //Recupera todas las tareas agregadas desde el backend, con basic Auth header
  //EL backend espera credenciales in el header de authorización: Authorization: Basic base64(username:password)
  getTasks(username: string, password: string): Observable<Task[]> {
    const headers = new HttpHeaders({
      //Manejo constante del header con Auth basic:
      Authorization: 'Basic ' + btoa(`${username}:${password}`)
    });

    // // desarrolla la petición con la url base y los headers
    return this.http.get<Task[]>(this.apiUrl, { headers });
  }


  //Por tiempo no pude realizar más operaciones. Pero las podemos probar desde postman
}
