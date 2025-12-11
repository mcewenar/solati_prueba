//Entidad

//ESTO SE ENVÍA A STPRING BOOT
export interface Task {
  //Identificador único
  id: number;
  title: string;
  //Opcional, corta descripción de la tarea
  description: string;
  //ESTO SE DEBERÍA CONTROLAR, PERO POR TIEMPO NO ALCANCÉ
  //Radio button, por ejemplo
  status: string; 
}
