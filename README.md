<h3>Gestão Escolar</h3>
<h4>Passos para rodar o programa</h4>
<ul>
  <li><strong>Back-end</strong></li>
  <ol>
    <li>Clonar o repositório em uma nova pasta.</li>
    <li>Abrir o IntelliJ e ir em importar projeto.</li>
    <li>Selecionar a pom.xml dentro da pasta onde o repositório foi clonado.</li>
    <li>Adicionar a pom como projeto Maven.</li>
    <li>Após terminar de baixar as dependências, abrir o terminal do IntelliJ e entrar no dirétorio 'docker'.</li>
    <li>Digitar o comando 'docker-compose -p gestao-escolar up' e dar um enter.</li>
    <li>Quando o container estiver criado, rodar a aplicação na main.</li>
    <li>Já vão estar criados dados para emissão do boletim.</li>
  </ol>
  <br>
  <li><strong>Front-end</strong></li>
  <ol>
    <li>Em um novo terminal, navegar até dentro da pasta 'front-end'.</li>
    <li>Instalar o plugin http-server pelo seguinto comando:</li>
    <strong>npm install -g http-server</strong>
    <li>Rodar o servidor web pelo comando:</li>
    <strong>http-server</strong>
    <li>Abrir um dos links que irá aparecer no console do terminal.</li>
  </ol>
  <br>
  <li><strong>Banco de dados</strong></li>
  <ol>
    <li>Para acompanhar o banco de dados, abrir o seu gerenciador de banco de dados de preferência e definir uma nova conexão com o nome:</li>
    <strong>localhost</strong>
    <li>Porta:</li>
    <strong>1477</strong>
    <li>Usuário:</li>
    <strong>sa</strong>
    <li>Senha:</li>
    <strong>Schooling123</strong>
  </ol>
</ul>
