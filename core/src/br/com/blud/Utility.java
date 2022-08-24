package br.com.blud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Utility {

    public static final AssetManager _assetManager = new AssetManager();
    public static final String TAG = Utility.class.getSimpleName();
    public static InternalFileHandleResolver _filePathResolver = new InternalFileHandleResolver();

    public static void unloadAsset(String assetFileNamePath){
        //Quando o gerenciador de ativos terminar de carregar
        if(_assetManager.isLoaded(assetFileNamePath)){
            _assetManager.unload(assetFileNamePath);
        }else{
            Gdx.app.debug(TAG,
                    "Asset não carregado; Nada para descarregar:"+
                    assetFileNamePath);
        }
    }


    public static float loadCompleted(){
        return _assetManager.getProgress();
    }
    public static int numberAssetsQueued(){
        return _assetManager.getQueuedAssets();
    }
    public static boolean updateAssetLoading(){
        return _assetManager.update();
    }
    public static boolean isAssetLoaded(String fileName){
        return _assetManager.isLoaded(fileName);
    }

    public static void loadMapAsset(String mapFilenamePath){
        if( mapFilenamePath == null || mapFilenamePath.isEmpty() ){
            return;
        }
        //carrega o ativo(asset)
        if( _filePathResolver.resolve(mapFilenamePath).exists() ){
            _assetManager.setLoader(
                    TiledMap.class, new TmxMapLoader(_filePathResolver));
            _assetManager.load(mapFilenamePath, TiledMap.class);
            //Ate adicionarmos a tela de carregamento,
            //apenas bloquear ate carregarmos o mapa
            _assetManager.finishLoadingAsset(mapFilenamePath);
            Gdx.app.debug(TAG, "Map loaded!: " + mapFilenamePath);
        }
        else{
            Gdx.app.debug(TAG, "Map doesn’t exist!: " + mapFilenamePath );
        }
    }
    public static TiledMap getMapAsset(String mapFilenamePath){
        TiledMap map = null;
        // quando o carregador de ativos terminar de carregar
        if(_assetManager.isLoaded(mapFilenamePath) ){
            map =_assetManager.get(mapFilenamePath,TiledMap.class);
        } else {
            Gdx.app.debug(TAG, "Map is not loaded: " + mapFilenamePath );
        }
        return map;
    }

    public static void loadTextureAsset(String textureFilenamePath){
        if( textureFilenamePath == null ||
                textureFilenamePath.isEmpty()){
            return;
        }
        //carrega o ativo
        if( _filePathResolver.resolve(textureFilenamePath).exists() ){
            _assetManager.setLoader(Texture.class,
                    new TextureLoader(_filePathResolver));
            _assetManager.load(textureFilenamePath, Texture.class);
            //Ate adicionarmos a tela de carregamento,
            //apenas bloquear ate carregarmos o mapa
            _assetManager.finishLoadingAsset(textureFilenamePath);
        }
        else{
            Gdx.app.debug(TAG, "Texture doesn’t exist!: " +
                    textureFilenamePath );
        }
    }
    public static Texture getTextureAsset(String textureFilenamePath){
        Texture texture = null;
        // Quando o gerenciador de ativos terminar de carregar
        if(_assetManager.isLoaded(textureFilenamePath) ){
            texture =
                    _assetManager.get(textureFilenamePath,Texture.class);
        } else {
            Gdx.app.debug(TAG, "Texture is not loaded: " +
                    textureFilenamePath );
        }
        return texture;
    }
}

