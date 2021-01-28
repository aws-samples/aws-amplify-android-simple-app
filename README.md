

# AWS - Android APP
El siguiente codigo pertenece al [blog post](https://aws.amazon.com/es/blogs/aws-spanish/creando-aplicaciones-nativas-en-android-con-aws/) en español y es ejemplo para la implementación de un código  escrito en Java y usando distintos servicios de AWS como:

 1. [AWS Amplify](https://aws.amazon.com/es/amplify/)
 2. [Amazon Cognito](https://aws.amazon.com/es/cognito/)
 3. [Amazon API Gateway](https://aws.amazon.com/es/api-gateway/)
 4. [AWS Lambda](https://aws.amazon.com/es/lambda/)
 5. [DynamoDB](https://aws.amazon.com/es/dynamodb/)

![architecture](https://github.com/aws-samples/aws-amplify-android-simple-app/blob/main/screencaptures/architecture.png?raw=true)


## Documentación
**Android**
Los [Componentes de Material UI para Android](https://material.io/components/android/) son componentes de UI modulares y personalizables que implementan Material Design. Este repositorio utiliza como base el código del laboratorio ["Building Beautiful Apps Faster"](https://codelabs.developers.google.com/codelabs/mdc-android/index.html).

**Amplify**
Puede inicializar su proyecto de Amplify siguiendo los pasos publicados en la [documentación oficial](https://docs.amplify.aws/cli/start/install#option-1-watch-the-video-guide).

## Integración con Amplify

Una vez creado el primer Activity, debemos configurar Amplify en nuestro proyecto. Para ello, primero debemos descargar la CLI para interactuar desde la terminal:

    npm install -g @aws-amplify/cli

Posteriormente debemos configurar el proyecto con un nombre, una región y un usuario IAM (Identity and Access Management) con los permisos que sean necesarios. Para realizarlo, ejecute el siguiente comando:

    amplify configure

Para comenzar a provisionar recursos en AWS, primero debemos inicializar el proyecto situado en el directorio root con el comando:

    amplify init

El próximo paso es añadir a nuestro proyecto de Amplify el recurso de autenticación. Lo logramos ejecutando un comando y estableciendo los parámetros que queremos. En nuestro caso será por default. El comando es:

    amplify add auth

Podes seguir el ejemplo de agregar autenticación u otro módulo desde la documentación oficial de [Amplify con Android](https://docs.amplify.aws/lib/auth/getting-started/q/platform/android#check-the-current-auth-session).

## API Gateway
El modelo de datos es meramente representativo. Se debería cambiar el endpoint de la API en *APIInfo* y *APIConstants* y el tipo de dato en el directorio *Model*.
