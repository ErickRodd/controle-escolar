<h3>Gestão Escolar</h3>
<h4>Passos para rodar o programa</h4>
<ul>
  <li><strong>Back-end</strong></li>
  <ol>
    <li>Clonar o repositório em uma nova pasta.</li>
    <li>Nessa pasta dar um checkout na branch 'dev'(se aparecer a mensagem de Untracked files prevent checkout, deletar a pasta .idea).</li>
    <li>Fechar esse projeto e ir em 'Import project'.</li>
    <li>Selecionar a pasta 'back-end' dentro da pasta do projeto.</li>
    <li>Ao aparecer a mensagem de Non-managed pom.xml file, adicionar como projeto Maven.</li>
    <li>Após terminar de baixar as dependências, abrir o terminal do IntelliJ e entrar no dirétorio 'docker'.</li>
    <li>Digitar o comando 'docker-compose up' e dar um enter.</li>
    <li>Quando o container estiver criado, rodar a aplicação na main.</li>
    <li>Já vão estar criados dados para emissão do boletim.</li>
  </ol>
  <br>
  <li><strong>Front-end</strong></li>
  <ol>
    <li>No VSCode, baixar a extensão Live Server.</li>
    <li>Abrir a pasta 'front-end' dentro da pasta do projeto.</li>
    <li>Abrir o arquivo 'index.html' e rodar o Live Server.</li>
  </ol>
  <br>
  <li><strong>Banco de dados</strong></li>
  <ol>
    <li>Se quiser acompanhar o banco de dados, fornecedor o server name 'localhost' em algum gerenciador de banco de dados.</li>
    <li>Logar com o usuário 'sa' e senha 'Hbsis123'.</li>
  </ol>
</ul>
