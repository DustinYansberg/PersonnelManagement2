import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'error',
  standalone: true
})
export class ErrorPipe implements PipeTransform {

  transform(value: any, input: string, length: number): string {
    let errorMessage = '';
    //check if any input
    if (value!== null){
      //if blank notify user they must enter something
      if(value['required']==true){
        errorMessage=input + ' is required!'
      }
      //if too short notify user that it must be longer
      if(value['minlength']){
        errorMessage=input + ' must be ' + length +' characters long!'
      }
      if(value['email']){
        errorMessage=input + ' must be follow xxx@xxx.com format'
      }
    }
    
    //if no error, return blank (falsy) string
    return errorMessage;
  }
}
