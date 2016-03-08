// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   Auth.java

package com.gusedu.bean;
 
import com.gusedu.dao.AuthService;
import com.gusedu.dao.UsuarioService;
import com.gusedu.dao.impl.AuthServiceImpl;
import com.gusedu.dao.impl.UsuarioServiceImpl;
import com.gusedu.model.Persona;
import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;
import com.gusedu.util.StaticUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class Auth {

            AuthService authService;
            UsuarioService usuarioService;
            private String username;
            private String password;
            public Usuario userLogged;
            
            	private String passactual;
	        private String passnueva;
	        private String passrepeat;

            public Auth() {
                authService = new AuthServiceImpl();
                usuarioService = new UsuarioServiceImpl();
/*  38*/        userLogged = new Usuario();
/*  39*/        userLogged.setTipoUsuario(new TipoUsuario());
/*  40*/        userLogged.setPersona(new Persona());
                passactual="";
                passnueva="";
                passrepeat="";
            }

            public Usuario getUserLogged() {
/*  47*/        return userLogged;
            }

            public void setUserLogged(Usuario userLogged) {
/*  54*/        this.userLogged = userLogged;
            }

            public String getUsername() {
/*  58*/        return username;
            }

            public void setUsername(String username) {
/*  62*/        this.username = username;
            }

            public String getPassword() {
/*  66*/        return password;
            }

            public void setPassword(String password) {
/*  70*/        this.password = password;
            }

    public String getPassactual() {
        return passactual;
    }

    public void setPassactual(String passactual) {
        this.passactual = passactual;
    }

    public String getPassnueva() {
        return passnueva;
    }

    public void setPassnueva(String passnueva) {
        this.passnueva = passnueva;
    }

    public String getPassrepeat() {
        return passrepeat;
    }

    public void setPassrepeat(String passrepeat) {
        this.passrepeat = passrepeat;
    }
            

          public String login() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			if (username == null) {
				StaticUtil.errorMessage("Error ", "El usuario no es válido");
				return null;
			}
			if (username.isEmpty()) {
				StaticUtil.errorMessage("Error ", "Debe ingresar un usuario");
				return null;
			}
			if (password == null) {
				StaticUtil.errorMessage("Error ", "La contraseña no es válida");
				return null;
			}
			if (password.isEmpty()) {
				StaticUtil.errorMessage("Error ", "Debe ingresar una contraseña");
				return null;
			}

			Usuario usuario = authService.find(username, password);

			if (usuario == null) {
				StaticUtil.errorMessage("Los datos ingresados no son correctos", "");
				return null;
			} else {
                            usuarioService.SP_registro(username,usuario.getUsuEmpresa());
				if(usuario.getPersona()==null)
				{
					StaticUtil.correctMesage("Éxito ", "Bienvenido al sistema");
					//StaticUtil.keepMessages();
				}else
				{
					StaticUtil.correctMesage("Éxito ", "Bienvenido al sistema "+usuario.getPersona().getPerNombres()+" "+usuario.getPersona().getPerApellidoP()+" "+usuario.getPersona().getPerApellidoM());
					//StaticUtil.keepMessages();
				}
				//authService.prueba();
				request.getSession().setAttribute("userLogged", usuario);				
				setUserLogged(usuario);	
                                //FacesContext fc = FacesContext.getCurrentInstance();

                               // usuario.getTipoUsuario().getTusCodigo()==2 || usuario.getTipoUsuario().getTusCodigo()==3
				 if(usuario.getTipoUsuario().getTusCodigo()==2  ) // Terapeuta
				 {
					return "/web/Principal?faces-redirect=true";
				 }
                                 if(usuario.getTipoUsuario().getTusCodigo()==3  ) // Administrador
				 {
					return "/web/Principal?faces-redirect=true";
				 }
                                 if(usuario.getTipoUsuario().getTusCodigo()==5  ) // Terapeuta Ext
				 {
					return "/web/Principal?faces-redirect=true";
				 }
                                 if(usuario.getTipoUsuario().getTusCodigo()==4)//Recepcionista
                                 {
                                        return "/web/Citas?faces-redirect=true";
                                 }
				/*if(usuario.getUsuTipoUsuario().getIdTipoUsuario()==3){
					return "/mobile/index.jsf";
				}*/
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

            public String cancel() {
/* 132*/        username = "";
/* 133*/        password = "";
/* 134*/        userLogged = new Usuario();
/* 135*/        return "/home.xhtml?faces-redirect=true";
            }

            public String logout() {
/* 139*/        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
/* 140*/        return "/home.xhtml?faces-redirect=true";
            }
            
            	public void cleaner()
	{
		passnueva="";
		passactual="";
		passrepeat="";
	}
                public void cambio()
	{
		System.out.println("PASS ACTUAL : "+passactual +"|| PASS LOG"+userLogged.getUsuPassword());
		if(!passactual.equals(userLogged.getUsuPassword()))
		{
			StaticUtil.errorMessage("Error", "Por favor ingrese su contraseña actual");
			return;
		}
		if(!passnueva.equals(passrepeat))
		{
			StaticUtil.errorMessage("Error", "Las password ingresadas no coinciden");
			return;
		}
		userLogged.setUsuPassword(passnueva);
		usuarioService.updateUsuario(userLogged);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgpass').hide();");
		StaticUtil.correctMesage("Éxito", "Se ha cambiado la contraseña");
		cleaner();
		context.update("formpass");
		
	}
}
