import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { provideHttpClient } from '@angular/common/http';
import { App } from './app/app';


/**
 * Inicializa la aplicación Angular usando bootstrapApplication,
 * una API moderna introducida en Angular 14 que permite
 * inicializar aplicaciones sin necesidad de un módulo raíz (AppModule).
 */
bootstrapApplication(App, {
  providers: [
    provideHttpClient(), //HttpClient como servicio global
  ],
}).catch(err => console.error(err));