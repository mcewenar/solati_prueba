
//Component: Required to create an Angular component.
//CommonModule: Provides Angular directives like *ngIf, *ngFor, etc.
//FormsModule: Enables [(ngModel)] for two-way data binding in your inputs.
//TaskService: Service that calls your Spring Boot backend.
//Task: Interface representing task objects returned from the API.
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskService } from './task.service';
import { Task } from './task.model';
//ROOT COMPONENT QUE SE RENDERIZA EN index.html
@Component({
  selector: 'app-root',
  standalone: true, //No necesita un angular module (AppModule)
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {

  username = '';
  password = '';
  error: string | null = null; //Guarda los errores para mostrarlos
  loggedIn = false; //Usado para mostrar el logo de Log In. Falso, muestra el logo para loguearse, true, no lo muestra

  tasks: Task[] = []; //Con una estructura de Array, este lista todas las tareas retrornadas desde el servidor 

  //Inyeccion de dependencias para poder usar los métodos del servicios y comunicarse con el backend
  constructor(private taskService: TaskService) {}

  login() {
    //Log method cuando clickeas en el botón de login
    this.error = null;

    //llama al servicio y le pasa las credencioales atrapadas desde el formulario en el HTTP
    //Los observables son estructuras (patrón de diseño observador) que suscribe al observable. En este caso 
    //eL servicio Observa detenidamente la emisión de datos
    this.taskService.getTasks(this.username, this.password).subscribe({
      //Guarda tareas y cambia el estado del booleando loggenIn
      next: tasks => {
        this.tasks = tasks;
        this.loggedIn = true;
      },
      //En caso de falla
      error: err => {
        console.error(err);
        this.error = 'Invalid username or password';
        //Para que no ingrese al log
        this.loggedIn = false;
        //reset
        this.tasks = [];
      }
    });
  }
//Cuando te deslogueas, se resetean todas las variables, para que, al ingresar, nuevbamente tengas que ingresar credenciales.
  logout() {
    this.loggedIn = false;
    this.username = '';
    this.password = '';
    this.error = null;
    this.tasks = [];
  }
}
